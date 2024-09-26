package pl.wmsdev.unisearch.gpt;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import pl.wmsdev.unisearch.dto.QuestionAndAnswer;
import pl.wmsdev.unisearch.model.QuestionResponse;
import pl.wmsdev.unisearch.repository.QuestionResponseRepository;
import pl.wmsdev.unisearch.utils.RetryWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GPTSurveyFacade {

    private final GPTPromptExecutor promptExecutor;
    private final QuestionResponseRepository questionResponseRepository;

    private static final String PROMPT = """
            Wybierz %d najbardziej pasujących opcji spośród: %s dla osoby która wypełniła 
            następującą ankietę: %s
                        
            Propozycje uszereguj w kolejności od najbardziej pasującej do najmniej pasującej 
            w postaci numerowanej listy, na przykład
            1 Najbardziej pasująca rzecz
            2 Mniej pasująca rzecz
            """;
    private final Pattern ENTRY_PATTERN = Pattern.compile("\\d(.*?)\\n");

    public <T> List<T> getSuggestions(List<QuestionAndAnswer> questionare, List<T> allPossibleChoices, long limit) {
        return getSuggestions(questionare, allPossibleChoices, limit, Object::toString);
    }

    public <T> List<T> getSuggestions(List<QuestionAndAnswer> questionare, List<T> allPossibleChoices, long limit,
                                      Function<T, String> stringifyFunction) {
        Map<String, T> toStringToObject = allPossibleChoices.stream()
                .collect(Collectors.toMap(stringifyFunction, Function.identity()));
        return RetryWrapper.retryIf(3, () -> askChat(questionare.stream()
                        .filter(question -> question.answer() != null && !question.answer().isEmpty())
                        .distinct()
                        .toList(), Math.min(allPossibleChoices.size(), limit), toStringToObject),
                () -> allPossibleChoices, list -> !list.isEmpty());
    }

    @NotNull
    private <T> List<T> askChat(List<QuestionAndAnswer> questionare, long limit, Map<String, T> toStringToObject) {
        persistQuestionsAndAndswers(questionare);
        return parseToListOfObjects(promptExecutor.executePrompt(PROMPT.formatted(limit, String.join("; ", toStringToObject.keySet()), questionare))).stream()
                .map(s -> s.replaceAll(";", "").strip())
                .filter(toStringToObject::containsKey)
                .map(toStringToObject::get)
                .limit(limit)
                .toList();
    }

    private void persistQuestionsAndAndswers(List<QuestionAndAnswer> questionnaire) {
        questionnaire.parallelStream().map(QuestionResponse::of).forEach(questionResponseRepository::save);
    }

    public List<String> parseToListOfObjects(String textToPrase) {
        List<String> result = new ArrayList<>();
        for (var line : textToPrase.split("\n")) {
            line = line + "\n";
            var matcher = ENTRY_PATTERN.matcher(line);
            if (matcher.find()) {
                result.add(sanitizeString(matcher.group().replace("\n", "")
                        .replaceFirst("\\d", "")
                        .replaceAll("\\.", "")
                        .replaceAll("\\(", "")
                        .replaceAll("\\)", "")
                        .strip()));
            }
        }
        return result;
    }

    private String sanitizeString(String s) {
        if (s.charAt(0) == ',') {
            return s.replaceFirst(",", "")
                    .strip();
        } else {
            return s;
        }
    }

}

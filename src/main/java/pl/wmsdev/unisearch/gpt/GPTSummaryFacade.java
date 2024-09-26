package pl.wmsdev.unisearch.gpt;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import pl.wmsdev.unisearch.dto.QuestionAndAnswer;
import pl.wmsdev.unisearch.model.University;
import pl.wmsdev.unisearch.utils.RetryWrapper;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GPTSummaryFacade {

    private static final String PROMPT = """
            Podsumuj następujący tekst w około %d słowach. Podsumowanie przedstaw w drugiej osobie liczby 
            pojedynczej. Spraw, żeby podsumowanie brzmiało przyjaźnie i było pozytywne:
            %s
            """;
    private final GPTPromptExecutor promptExecutor;


    public String summarize(String text, int numberOfWords) {
        return RetryWrapper.retryOrElse(3, () -> promptExecutor.executePrompt(PROMPT.formatted(numberOfWords, text)),
                () -> "");
    }

    public String summarize(University pickedByChat, List<QuestionAndAnswer> questionAndAnswers) {
        return summarize(questionAndAnswers + """
                 Sugerowany uniwersytet i kierunek: %s
                """.formatted(prettify(pickedByChat)), 100);
    }

    @NotNull
    private String prettify(University first) {
        return first.getUniversity() + ", " + first.getMajor() + ", " + first.getStudyLanguage() + ", " + first.getForm();
    }


}

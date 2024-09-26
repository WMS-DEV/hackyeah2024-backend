package pl.wmsdev.unisearch.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import pl.wmsdev.unisearch.dto.UniSearchRequest;
import pl.wmsdev.unisearch.dto.UniSearchResponse;
import pl.wmsdev.unisearch.gpt.GPTSummaryFacade;
import pl.wmsdev.unisearch.gpt.GPTSurveyFacade;
import pl.wmsdev.unisearch.model.StudyLevel;
import pl.wmsdev.unisearch.model.University;
import pl.wmsdev.unisearch.model.UniversityData;
import pl.wmsdev.unisearch.repository.UniversityDataRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UniSearchFinalQueryService {

    private final UniversityDataRepository dataRepository;
    private final GPTSurveyFacade surveyFacade;
    private final GPTSummaryFacade summaryFacade;
    private final UniversityFactory universityFactory;


    public UniSearchResponse processFormSubmission(UniSearchRequest request) {
        List<UniversityData> possibleEntries = new ArrayList<>();
        for(var university : request.selectedUniversities()) {
            for(var major : request.selectedMajors()) {
                var foundEntry =
                        dataRepository.findByMajorLikeIgnoreCaseAndUniversityLikeIgnoreCaseAndLevelIsIn(major, university, request.level().stream()
                                .map(StudyLevel::getColName)
                                .toList());
                possibleEntries.addAll(foundEntry);
            }
        }
        return handleEntries(possibleEntries.stream()
                .map(universityFactory::getUniversity)
                .toList(), request);
    }

    public UniSearchResponse handleEntries(List<University> possibleEntries, UniSearchRequest request) {
        if(possibleEntries.isEmpty()) {
            return new UniSearchResponse(Collections.emptyList(), "Niestety nie byliśmy w stanie podpowiedzieć Ci " +
                    "jaką uczelnie wybrać. Nasz zespół został powiadomiony o Twoim przypadku! Prosimy spróbuj ponownie :)");
        }
        var pickedByChat = surveyFacade.getSuggestions(request.allQuestionsAndAnswers(), possibleEntries, 10,
                this::prettify);
        return new UniSearchResponse(pickedByChat, summaryFacade.summarize(pickedByChat.get(0), request.allQuestionsAndAnswers()));
    }

    @NotNull
    private String prettify(University first) {
        return first.getId() + " ," + first.getUniversity() + ", " + first.getMajor() + ", " + first.getCityName();
    }

}

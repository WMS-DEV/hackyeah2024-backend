package pl.wmsdev.unisearch.service;

import org.springframework.web.bind.annotation.RequestBody;
import pl.wmsdev.unisearch.dto.QuestionAndAnswer;
import pl.wmsdev.unisearch.dto.UniSearchRequest;
import pl.wmsdev.unisearch.dto.UniSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wmsdev.unisearch.gpt.GPTSummaryFacade;
import pl.wmsdev.unisearch.gpt.GPTSurveyFacade;
import pl.wmsdev.unisearch.model.StudyForm;
import pl.wmsdev.unisearch.model.StudyLevel;
import pl.wmsdev.unisearch.repository.UniversityCitiesRepository;
import pl.wmsdev.unisearch.repository.UniversityDataRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class UniSearchService {

    private final UniversityDataRepository dataRepository;
    private final GPTSurveyFacade surveyFacade;
    private final UniSearchFinalQueryService queryService;
    private final UniversityFactory universityFactory;
    private final GPTSummaryFacade summaryFacade;
    private final UniversityCitiesRepository citiesRepository;


    public UniSearchResponse query(UniSearchRequest request) {
        return queryService.processFormSubmission(request);
    }

    public UniSearchResponse random() {
        var random = new Random();
        return new UniSearchResponse(Stream.generate(() -> universityFactory.getUniversity(dataRepository.findById((long) (100 + random.nextInt(600))).get()))
                .limit(5)
                .toList(), "");
    }

    public List<String> suggestProfile(List<QuestionAndAnswer> questionAndAnswers) {
        return surveyFacade.getSuggestions(questionAndAnswers, List.of("praktyczny", "ogólnoakademicki"), 1);
    }

    public List<String> suggestClassification(List<QuestionAndAnswer> questionAndAnswers) {
        return surveyFacade.getSuggestions(questionAndAnswers, dataRepository.getDistinctClassifications(), 20);
    }

    public List<String> suggestMajor(List<QuestionAndAnswer> questionAndAnswers, List<String> classifications,
                                     List<StudyLevel> studyLevels, List<StudyForm> studyForms) {
        return surveyFacade.getSuggestions(questionAndAnswers,
                dataRepository.getDistinctMajorsForClassificationAndStudyForm(classifications, studyLevels.stream().map(StudyLevel::getColName).toList(),
                        studyForms.stream().map(StudyForm::getColName).toList()), 20);
    }

    public List<String> suggestUniversity(List<QuestionAndAnswer> questionAndAnswers, List<String> majors) {
        return surveyFacade.getSuggestions(questionAndAnswers, dataRepository.getDistinctUniversitiesByMajors(majors).stream()
                .map(e -> e + ", " + citiesRepository.getCityForUniversity(e))
                .toList(), 15).stream()
                .map(s -> s.split(",")[0])
                .toList();
    }

    public String describe(String universityId, List<QuestionAndAnswer> questionAndAnswers) {
        return summaryFacade.summarize(universityFactory.getUniversity(dataRepository.findById(Long.valueOf(universityId))
                .orElseThrow()), questionAndAnswers);
    }
}

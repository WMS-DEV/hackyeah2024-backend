package pl.wmsdev.unisearch.dto;

import pl.wmsdev.unisearch.model.StudyLevel;

import java.util.List;

public record UniSearchRequest(List<StudyLevel> level, List<QuestionAndAnswer> allQuestionsAndAnswers,
                               List<String> selectedMajors, List<String> selectedUniversities) {


}

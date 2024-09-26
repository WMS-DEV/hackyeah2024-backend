package pl.wmsdev.unisearch.dto;

import pl.wmsdev.unisearch.model.StudyForm;
import pl.wmsdev.unisearch.model.StudyLevel;

import java.util.List;

public record SuggestMajorRequest(List<QuestionAndAnswer> questionAndAnswers,
                                  List<String> classifications, List<StudyLevel> studyLevels,
                                  List<StudyForm> studyForms) {
}

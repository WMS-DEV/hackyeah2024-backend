package pl.wmsdev.unisearch.dto;

import java.util.List;

public record SuggestUniversityRequest(List<QuestionAndAnswer> questionAndAnswers,
                                       List<String> majors) {
}

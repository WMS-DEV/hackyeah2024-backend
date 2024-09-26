package pl.wmsdev.unisearch.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import pl.wmsdev.unisearch.dto.QuestionAndAnswer;

@Data
@Builder
public class QuestionResponse {
    @Id
    private Long id;
    private String question;
    private String answer;

    public static QuestionResponse of(QuestionAndAnswer questionAndAnswer) {
        return QuestionResponse.builder()
                .question(questionAndAnswer.question())
                .answer(questionAndAnswer.answer())
                .build();
    }
}

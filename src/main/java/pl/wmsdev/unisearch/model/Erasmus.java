package pl.wmsdev.unisearch.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Erasmus {
    @Id
    private Long id;
    private String keyAction;
    private String actionType;
    private Integer fundingYear;
    private String projectLink;
    private String university;
}

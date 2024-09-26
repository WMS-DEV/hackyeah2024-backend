package pl.wmsdev.unisearch.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class PerspektywyData {
    @Id
    private Long id;
    private String name;
    private String place2023;
    private String place2022;
    private String place2021;
    private Double points;
    private String rankingType;

}

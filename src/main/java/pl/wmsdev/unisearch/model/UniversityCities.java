package pl.wmsdev.unisearch.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class UniversityCities {
    @Id
    private Long id;
    private String university;
    private String cityName;
    private Boolean isPublic;
    private String logoLink;
}

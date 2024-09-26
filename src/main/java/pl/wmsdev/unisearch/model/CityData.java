package pl.wmsdev.unisearch.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class CityData {

    @Id
    private Long id;
    private String name;
    private int foodCost;
    private int rentalCost;
    private int entertainmentCost;

}

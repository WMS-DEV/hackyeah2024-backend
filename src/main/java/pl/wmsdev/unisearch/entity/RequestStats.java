package pl.wmsdev.unisearch.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestStats {

    @Id
    Long id;
    String ip;
    String requestJson;
    String responseJson;
    String url;
    LocalDateTime ts;
}

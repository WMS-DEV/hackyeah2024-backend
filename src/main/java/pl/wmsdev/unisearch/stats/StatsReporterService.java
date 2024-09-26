package pl.wmsdev.unisearch.stats;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.wmsdev.unisearch.entity.RequestStats;
import pl.wmsdev.unisearch.repository.RequestStatsRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class StatsReporterService {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules();
    private final RequestStatsRepository requestStatsRepository;


    public <T> T report(HttpServletRequest request, Object argument, Supplier<T> action) {
        var result = action.get();
        asyncFlush(request.getRequestURL().toString(), request.getRemoteAddr(), result, argument);
        return result;
    }

    public <T> T report(HttpServletRequest request, Supplier<T> action, Object... args) {
        var result = action.get();
        asyncFlush(request.getRequestURL().toString(), request.getRemoteAddr(), result, args);
        return result;
    }

    @SneakyThrows
    @Async
     <T> void asyncFlush(String url, String ip, T result, Object... argument) {
        var stats = RequestStats.builder()
                .requestJson(objectMapper.writeValueAsString(argument))
                .responseJson(objectMapper.writeValueAsString(result))
                .url(url)
                .ip(ip)
                .ts(LocalDateTime.now())
                .build();
        requestStatsRepository.save(stats);
    }

}


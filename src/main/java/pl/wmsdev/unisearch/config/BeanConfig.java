package pl.wmsdev.unisearch.config;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.wmsdev.unisearch.gpt.GPTPromptExecutor;

import java.time.Duration;

@Configuration
public class BeanConfig {

    @Bean
    public GPTPromptExecutor gptPromptExecutor() {
        var service = new OpenAiService("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", Duration.ofSeconds(250));
        return new GPTPromptExecutor(service);
    }
}

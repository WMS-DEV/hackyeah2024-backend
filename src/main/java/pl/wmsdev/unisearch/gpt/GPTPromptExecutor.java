package pl.wmsdev.unisearch.gpt;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.util.List;

public class GPTPromptExecutor {
    private final OpenAiService openAiService;

    public GPTPromptExecutor(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    public String executePrompt(String prompt) {
        return openAiService.createChatCompletion(
                ChatCompletionRequest.builder()
                        .messages(List.of(createMessageFromPrompt(prompt)))
                        .model("gpt-3.5-turbo")
                        .build()
        ).getChoices().get(0).getMessage().getContent();
    }

    private ChatMessage createMessageFromPrompt(String prompt) {
        return new ChatMessage("user", prompt);
    }
}

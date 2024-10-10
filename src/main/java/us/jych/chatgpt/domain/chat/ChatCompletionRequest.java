package us.jych.chatgpt.domain.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionRequest implements Serializable {

    /** Default model */
    private String model = Model.GPT_3_5_TURBO.getCode();
    /** Question description */
    private List<Message> messages;
    /** Controls the randomness; value between 0 and 2. Higher values (e.g., 0.8) make the output more random, while lower values (e.g., 0.2) make it more focused and deterministic */
    private double temperature = 0.2;
    /** Diversity control; an alternative to temperature sampling is called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So, 0.1 means only considering tokens comprising the top 10% probability mass */
    @JsonProperty("top_p")
    private Double topP = 1d;
    /** The number of completions generated for each prompt */
    private Integer n = 1;
    /** Whether to output in streaming mode; results are returned incrementally */
    private boolean stream = false;
    /** Stop sequence; the token where the output should stop */
    private List<String> stop;
    /** Limit on the output tokens; range 0 ~ 4096 */
    @JsonProperty("max_tokens")
    private Integer maxTokens = 2048;
    /** Frequency penalty; reduces the likelihood of the model repeating the same line */
    @JsonProperty("frequency_penalty")
    private double frequencyPenalty = 0;
    /** Presence penalty; increases the likelihood of the model talking about new topics */
    @JsonProperty("presence_penalty")
    private double presencePenalty = 0;
    /** Generates multiple results but only shows the best one. This will consume more API tokens */
    @JsonProperty("logit_bias")
    private Map logitBias;
    /** User identifier to avoid duplicate requests */
    private String user;

    @Getter
    @AllArgsConstructor
    public enum Model {
        /** gpt-3.5-turbo */
        GPT_3_5_TURBO("gpt-3.5-turbo"),
        /** GPT-4 */
        GPT_4("gpt-4"),
        /** GPT-4 with extended context length */
        GPT_4_32K("gpt-4-32k"),
        ;
        private String code;
    }

}

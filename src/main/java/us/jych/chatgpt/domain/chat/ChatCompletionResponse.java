package us.jych.chatgpt.domain.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import us.jych.chatgpt.domain.other.Usage;

import java.io.Serializable;
import java.util.List;

@Data
public class ChatCompletionResponse implements Serializable {

    /** ID */
    private String id;
    /** Object */
    private String object;
    /** Model */
    private String model;
    /** Conversation */
    private List<ChatChoice> choices;
    /** Created */
    private long created;
    /** Usage */
    private Usage usage;
    /**
     * https://platform.openai.com/docs/api-reference/chat
     */
    @JsonProperty("system_fingerprint")
    private String systemFingerprint;

}

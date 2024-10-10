package us.jych.chatgpt.session;


import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import us.jych.chatgpt.domain.chat.ChatCompletionRequest;
import us.jych.chatgpt.domain.chat.ChatCompletionResponse;
import us.jych.chatgpt.domain.images.ImageRequest;
import us.jych.chatgpt.domain.images.ImageResponse;


public interface OpenAiSession {


    ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest);

    EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;

    ImageResponse imgResponse(ImageRequest request);

    ImageResponse genImages(String prompt);

}

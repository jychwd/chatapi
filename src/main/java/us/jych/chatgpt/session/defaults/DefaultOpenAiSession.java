package us.jych.chatgpt.session.defaults;


import cn.hutool.http.ContentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import us.jych.chatgpt.IOpenAiApi;
import us.jych.chatgpt.domain.chat.ChatCompletionRequest;
import us.jych.chatgpt.domain.chat.ChatCompletionResponse;
import us.jych.chatgpt.domain.images.ImageRequest;
import us.jych.chatgpt.domain.images.ImageResponse;
import us.jych.chatgpt.session.Configuration;
import us.jych.chatgpt.session.OpenAiSession;

public class DefaultOpenAiSession implements OpenAiSession {

    private final Configuration configuration;


    private final IOpenAiApi openAiApi;

    private final EventSource.Factory factory;



    public DefaultOpenAiSession(Configuration configuration) {
        this.configuration = configuration;
        this.openAiApi = configuration.getOpenAiApi();
        this.factory = configuration.createRequestFactory();
    }



    @Override
    public ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest) {
        return this.openAiApi.completions(chatCompletionRequest).blockingGet();
    }

    @Override
    public EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {

        if (!chatCompletionRequest.isStream()) {
            throw new RuntimeException("illegal parameter!");
        }


        Request request = new Request.Builder()

                .url(configuration.getApiHost().concat(IOpenAiApi.v1_chat_completions))
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), new ObjectMapper().writeValueAsString(chatCompletionRequest)))
                .build();

        return factory.newEventSource(request, eventSourceListener);
    }

    @Override
    public ImageResponse imgResponse(ImageRequest request) {
        return this.openAiApi.genImages(request).blockingGet();
    }

    @Override
    public ImageResponse genImages(String prompt) {

        ImageRequest request = ImageRequest.builder()
                .prompt(prompt).build();
        return this.openAiApi.genImages(request).blockingGet();
    }

}
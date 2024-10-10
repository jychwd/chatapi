package us.jych.test;



import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.junit.Before;
import org.junit.Test;
import us.jych.chatgpt.common.Constants;
import us.jych.chatgpt.domain.chat.ChatCompletionRequest;
import us.jych.chatgpt.domain.chat.ChatCompletionResponse;
import us.jych.chatgpt.domain.chat.Message;

import us.jych.chatgpt.domain.images.ImageEnum;
import us.jych.chatgpt.domain.images.ImageRequest;
import us.jych.chatgpt.domain.images.ImageResponse;
import us.jych.chatgpt.session.Configuration;
import us.jych.chatgpt.session.OpenAiSession;
import us.jych.chatgpt.session.OpenAiSessionFactory;
import us.jych.chatgpt.session.defaults.DefaultOpenAiSessionFactory;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;


@Slf4j
public class ApiTest {

    private OpenAiSession openAiSession;

    @Before
    public void test_OpenAiSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.setApiHost("https://api.openai.com/");
        configuration.setApiKey("apikey");


        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);

        this.openAiSession = factory.openSession();
    }

    @Test
    public void test_completion_stream() throws JsonProcessingException, InterruptedException {
        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .stream(true)
                .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER).content("hello").build()))
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .build();

        EventSource eventSource = openAiSession.chatCompletions(chatCompletion, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                log.info("res：{}", data);
            }
        });

        new CountDownLatch(1).await();
    }


    @Test
    public void test_chat_completions() {

        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER).content("1+1").build()))
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .build();

        ChatCompletionResponse chatCompletionResponse = openAiSession.completions(chatCompletion);

        chatCompletionResponse.getChoices().forEach(e -> {
            log.info("res：{}", e.getMessage());
        });
    }

    @Test
    public void test_image(){

        ImageResponse imageResponse02 = openAiSession.imgResponse(ImageRequest.builder()
                .prompt("scene")
                .size(ImageEnum.Size.size_256.getCode())
                .responseFormat(ImageEnum.ResponseFormat.B64_JSON.getCode()).build());
        log.info("res：{}", imageResponse02);
    }

}

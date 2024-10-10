package us.jych.chatgpt;


import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import us.jych.chatgpt.domain.chat.ChatCompletionRequest;
import us.jych.chatgpt.domain.chat.ChatCompletionResponse;
import us.jych.chatgpt.domain.images.ImageRequest;
import us.jych.chatgpt.domain.images.ImageResponse;


public interface IOpenAiApi {

    String v1_completions = "v1/completions";
    String v1_chat_completions = "v1/chat/completions";



    @POST("v1/chat/completions")
    Single<ChatCompletionResponse> completions(@Body ChatCompletionRequest chatCompletionRequest);

    @POST("v1/images/generations")
    Single<ImageResponse> genImages(@Body ImageRequest imageRequest);

}

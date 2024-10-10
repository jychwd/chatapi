package us.jych.chatgpt.session;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;
import us.jych.chatgpt.IOpenAiApi;


@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    @Getter
    @Setter
    private IOpenAiApi openAiApi;


    @Getter
    @Setter
    private OkHttpClient okHttpClient;

    @Getter
    @Setter
    @NotNull
    private String apiKey;

    @Getter
    @Setter
    private String apiHost;

    @Getter
    @Setter
    private String authToken;

    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(okHttpClient);
    }

}

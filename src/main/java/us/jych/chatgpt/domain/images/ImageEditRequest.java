package us.jych.chatgpt.domain.images;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;


@Slf4j
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ImageEditRequest extends ImageEnum implements Serializable {


    @NonNull
    private String prompt;

    @Builder.Default
    private Integer n = 1;

    @Builder.Default
    private String size = Size.size_256.getCode();

    @JsonProperty("response_format")
    @Builder.Default
    private String responseFormat = ResponseFormat.URL.getCode();
    @Setter
    private String user;

}
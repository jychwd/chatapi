package us.jych.chatgpt.domain.images;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class ImageResponse implements Serializable {

    private List<Item> data;

    private long created;
}

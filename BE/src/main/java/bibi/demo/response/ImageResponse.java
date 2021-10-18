package bibi.demo.response;

import bibi.demo.domain.Image;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class ImageResponse {

    @JsonIgnore
    private Long id;

    private String imageAddress;

    public ImageResponse(Long id, String imageAddress) {
        this.id = id;
        this.imageAddress = imageAddress;
    }

    public static ImageResponse toImageResponse(Image image) {
        return new ImageResponse(image.getId(), image.getImageAddress());
    }
}

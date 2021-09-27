package bibi.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class Image {

    @Id
    private Long id;

    @JsonProperty("has_image")
    private boolean hasImage;

    @JsonProperty("img_address")
    private String imgAddress;

    public Image() {
    }

    public Image(Long id, boolean hasImage) {
        this.id = id;
        this.hasImage = hasImage;
    }

    public Image(Long id, boolean hasImage, String imgAddress) {
        this(id, hasImage);
        this.imgAddress = imgAddress;
    }
}

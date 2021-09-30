package bibi.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("has_image")
    private boolean hasImage;

    @JsonProperty("image_address")
    private String imageAddress;

    public Image() {
    }

    public Image(Long id, boolean hasImage) {
        this.id = id;
        this.hasImage = hasImage;
    }

    public Image(Long id, boolean hasImage, String imageAddress) {
        this(id, hasImage);
        this.imageAddress = imageAddress;
    }
}

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

    @JsonProperty("flavor_id")
    private Long flavorId;

    @JsonProperty("image_address")
    private String imageAddress;

    public Image() {
    }

    public Image(Long id, Long flavorId, String content) {
        this.id = id;
        this.flavorId = flavorId;
        this.imageAddress = content;
    }
}

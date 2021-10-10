package bibi.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_address")
    private String imageAddress;

    public Image() {
    }

    public Image(Long id, String content) {
        this.id = id;
        this.imageAddress = content;
    }
}

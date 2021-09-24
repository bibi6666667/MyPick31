package bibi.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Image {

    @Id
    private Long id;
    private boolean hasImage;
    private String imgAddress;
}

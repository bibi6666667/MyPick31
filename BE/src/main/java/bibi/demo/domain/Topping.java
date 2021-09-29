package bibi.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name_kr")
    private String nameKR;
    @JsonProperty("name_en")
    private String nameEN;

    @JsonProperty("is_choco_coated")
    private boolean isChocoCoated;

    @JsonProperty("topping_type_id")
    private Long toppingTypeId;

    public Topping() {
    }

    public Topping(Long id, String nameKR, String nameEN, boolean isChocoCoated, Long toppingTypeId) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
        this.isChocoCoated = isChocoCoated;
        this.toppingTypeId = toppingTypeId;
    }
}

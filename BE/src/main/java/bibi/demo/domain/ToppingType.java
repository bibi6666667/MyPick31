package bibi.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class ToppingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name_kr")
    private String nameKR;
    @JsonProperty("name_en")
    private String nameEN;

    public ToppingType() {
    }

    public ToppingType(Long id, String nameKR, String nameEN) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
    }
}

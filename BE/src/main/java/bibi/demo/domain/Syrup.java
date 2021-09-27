package bibi.demo.domain;

import bibi.demo.domain.enums.SyrupCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Syrup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name_kr")
    private String nameKR;
    @JsonProperty("name_en")
    private String nameEN;

    private SyrupCategory syrupCategory;

    public Syrup() {
    }

    public Syrup(Long id, String nameKR, String nameEN, SyrupCategory syrupCategory) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
        this.syrupCategory = syrupCategory;
    }
}

package bibi.demo.domain;

import bibi.demo.domain.enums.AllergenType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "allergen")
public class Allergen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("allergen_type")
    private AllergenType allergenType;

    public Allergen() {
    }

    public Allergen(Long id, AllergenType allergenType) {
        this.id = id;
        this.allergenType = allergenType;
    }
}

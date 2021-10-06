package bibi.demo.dto;

import bibi.demo.domain.type.Allergen;
import lombok.Getter;

import java.util.List;

@Getter
public class Allergens {

    private List<Allergen> allergens;

    public Allergens(List<Allergen> allergens) {
        this.allergens = allergens;
    }
}

package bibi.demo.response.flavor;

import bibi.demo.domain.Allergen;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class AllergenResponse {

    @JsonIgnore
    private Long id;

    private String nameKR;
    private String nameEN;

    public AllergenResponse(Long id, String nameKR, String nameEN) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
    }

    public static AllergenResponse toAllergenResponse(Allergen allergen) {
        return new AllergenResponse(allergen.getId(), allergen.getNameKR(), allergen.getNameEN());
    }
}

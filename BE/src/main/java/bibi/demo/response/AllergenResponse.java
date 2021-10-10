package bibi.demo.response;

import bibi.demo.domain.Allergen;
import lombok.Getter;

@Getter
public class AllergenResponse {

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

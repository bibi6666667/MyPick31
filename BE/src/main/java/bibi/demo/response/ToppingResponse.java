package bibi.demo.response;

import bibi.demo.domain.Topping;
import lombok.Getter;

@Getter
public class ToppingResponse {

    private Long id;
    private String nameKR;
    private String nameEN;
    private Long toppingTypeId;

    public ToppingResponse(Long id, String nameKR, String nameEN, Long toppingTypeId) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
        this.toppingTypeId = toppingTypeId;
    }

    public static ToppingResponse toToppingResponse(Topping topping) {
        return new ToppingResponse(topping.getId(), topping.getNameKR(), topping.getNameEN(), topping.getToppingTypeId());
    }
}

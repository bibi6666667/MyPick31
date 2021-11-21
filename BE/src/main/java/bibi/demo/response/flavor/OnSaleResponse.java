package bibi.demo.response.flavor;

import bibi.demo.domain.OnSale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class OnSaleResponse {

    @JsonIgnore
    private Long id;

    private boolean isOnSale;

    public OnSaleResponse(Long id, boolean isOnSale) {
        this.id = id;
        this.isOnSale = isOnSale;
    }

    public static OnSaleResponse toOnSaleResponse(OnSale onSale) {
        return new OnSaleResponse(onSale.getId(), onSale.isOnSale());
    }
}

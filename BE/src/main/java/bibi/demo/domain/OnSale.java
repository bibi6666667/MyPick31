package bibi.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class OnSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("flavor_id")
    private Long flavorId;

    private boolean is_on_sale;

    public OnSale() {
    }

    public OnSale(Long id, Long flavorId, boolean is_on_sale) {
        this.id = id;
        this.flavorId = flavorId;
        this.is_on_sale = is_on_sale;
    }
}

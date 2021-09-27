package bibi.demo.dto;

import bibi.demo.domain.Topping;
import lombok.Getter;

import java.util.List;

@Getter
public class Toppings {

    private List<Topping> toppings;

    public Toppings(List<Topping> toppings) {
        this.toppings = toppings;
    }
}

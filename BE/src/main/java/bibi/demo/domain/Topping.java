package bibi.demo.domain;

import bibi.demo.domain.flavor.FlavorTopping;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_kr")
    private String nameKR;
    @Column(name = "name_en")
    private String nameEN;

    @Column(name = "topping_type_id")
    private Long toppingTypeId;

    @OneToMany(mappedBy = "topping")
    private List<FlavorTopping> flavorToppingList = new ArrayList<>();


    public Topping() {
    }

    public Topping(Long id, String nameKR, String nameEN, Long toppingTypeId) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
        this.toppingTypeId = toppingTypeId;
    }
}

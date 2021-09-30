package bibi.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FlavorAllergen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flavor_id")
    private Flavor flavor;

    @ManyToOne
    @JoinColumn(name = "allergen_id")
    private Allergen allergen;

    public FlavorAllergen() {
    }

    public FlavorAllergen(Long id, Flavor flavor, Allergen allergen) {
        this.id = id;
        this.flavor = flavor;
        this.allergen = allergen;
    }
}

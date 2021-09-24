package bibi.demo.domain;

import javax.persistence.*;

@Entity
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
}

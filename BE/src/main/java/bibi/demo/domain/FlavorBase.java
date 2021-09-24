package bibi.demo.domain;

import javax.persistence.*;

@Entity
public class FlavorBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flavor_id")
    private Flavor flavor;

    @ManyToOne
    @JoinColumn(name = "base_id")
    private Base base;
}

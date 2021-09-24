package bibi.demo.domain;

import javax.persistence.*;

@Entity
public class FlavorSyrup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flavor_id")
    private Flavor flavor;

    @ManyToOne
    @JoinColumn(name = "syrup_id")
    private Syrup syrup;
}

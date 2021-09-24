package bibi.demo.domain;

import javax.persistence.*;

@Entity
public class FlavorTopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flavor_id")
    private Flavor flavor;

    @ManyToOne
    @JoinColumn(name = "topping_id")
    private Topping topping;
}

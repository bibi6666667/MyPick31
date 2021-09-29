package bibi.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    public FlavorSyrup() {
    }

    public FlavorSyrup(Long id, Flavor flavor, Syrup syrup) {
        this.id = id;
        this.flavor = flavor;
        this.syrup = syrup;
    }
}

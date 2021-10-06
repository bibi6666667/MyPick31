package bibi.demo.domain.flavor;

import bibi.demo.domain.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    public FlavorBase() {
    }

    public FlavorBase(Long id, Flavor flavor, Base base) {
        this.id = id;
        this.flavor = flavor;
        this.base = base;
    }
}

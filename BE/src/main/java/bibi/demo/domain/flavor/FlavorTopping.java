package bibi.demo.domain.flavor;

import bibi.demo.domain.Topping;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "flavor_topping")
@Getter
@Setter
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

    public FlavorTopping() {
    }

    public FlavorTopping(Long id, Flavor flavor, Topping topping) {
        this.id = id;
        this.flavor = flavor;
        this.topping = topping;
    }
}

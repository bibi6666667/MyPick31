package bibi.demo.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OnSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_on_sale")
    private boolean isOnSale;

    public OnSale() {
    }

    public OnSale(Long id, boolean isOnSale) {
        this.id = id;
        this.isOnSale = isOnSale;
    }
}

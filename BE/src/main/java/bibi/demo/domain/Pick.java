package bibi.demo.domain;

import bibi.demo.domain.flavor.Flavor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Pick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flavor_id")
    private Long flavorId;

    @Column(name = "user_id")
    private Long userId;

//    @ManyToOne
//    @JoinColumn(name = "flavor_id")
//    private Flavor flavor;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    public Pick() {
    }

    public Pick(Long flavorId, Long userId) {
        this.flavorId = flavorId;
        this.userId = userId;
    }

    public Pick(Long id, Long flavorId, Long userId) {
        this(flavorId, userId);
        this.id = id;
    }
}

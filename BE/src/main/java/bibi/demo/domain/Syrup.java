package bibi.demo.domain;

import bibi.demo.domain.flavor.FlavorSyrup;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Syrup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_kr")
    private String nameKR;
    @Column(name = "name_en")
    private String nameEN;

    @Column(name = "syrup_type_id")
    private Long syrupTypeId;

    @OneToMany(mappedBy = "syrup")
    private List<FlavorSyrup> flavorSyrupList = new ArrayList<>();

    public Syrup() {
    }

    public Syrup(Long id, String nameKR, String nameEN, Long syrupTypeId) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
        this.syrupTypeId = syrupTypeId;
    }
}

package bibi.demo.domain;

import bibi.demo.domain.flavor.FlavorBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_kr")
    private String nameKR;
    @Column(name = "name_en")
    private String nameEN;

    private boolean isSherbet;
    private boolean isSorbet;

    @Column(name = "base_type_id")
    private Long baseTypeId;

    // @JsonIgnore
    @OneToMany(mappedBy="base")
    private List<FlavorBase> flavorBaseList = new ArrayList<>();

    public Base() {
    }

    public Base(Long id, String nameKR, String nameEN, boolean isSherbet, boolean isSorbet, Long baseTypeId) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
        this.isSherbet = isSherbet;
        this.isSorbet = isSorbet;
        this.baseTypeId = baseTypeId;
    }
}


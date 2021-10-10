package bibi.demo.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ToppingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_kr")
    private String nameKR;
    @Column(name = "name_en")
    private String nameEN;

    public ToppingType() {
    }

    public ToppingType(Long id, String nameKR, String nameEN) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
    }
}

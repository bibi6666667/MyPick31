package bibi.demo.domain.type;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class BaseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name_kr")
    private String nameKR;
    @JsonProperty("name_en")
    private String nameEN;

    public BaseType() {
    }

    public BaseType(Long id, String nameKR, String nameEN) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
    }
}

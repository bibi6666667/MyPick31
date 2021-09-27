package bibi.demo.domain;

import bibi.demo.domain.enums.BaseCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name_kr")
    private String nameKR;
    @JsonProperty("name_en")
    private String nameEN;

    private boolean isSherbet;
    private boolean isSorbet;

    private BaseCategory baseCategory;

    public Base() {
    }

    public Base(Long id, String nameKR, String nameEN, boolean isSherbet, boolean isSorbet, BaseCategory baseCategory) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
        this.isSherbet = isSherbet;
        this.isSorbet = isSorbet;
        this.baseCategory = baseCategory;
    }
}

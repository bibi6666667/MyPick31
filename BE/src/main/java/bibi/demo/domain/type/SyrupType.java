package bibi.demo.domain.type;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class SyrupType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_kr")
    private String nameKR;
    @Column(name = "name_en")
    private String nameEN;

    public SyrupType() {
    }

    public SyrupType(Long id, String nameKR, String nameEN) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
    }
}

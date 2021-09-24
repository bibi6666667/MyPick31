package bibi.demo.domain;

import bibi.demo.domain.enums.SyrupCategory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Syrup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameKR;
    private String nameEN;
    private SyrupCategory syrupCategory;
}

package bibi.demo.domain;

import bibi.demo.domain.enums.AllergenType;

import javax.persistence.*;

@Entity
@Table(name = "allergen")
public class Allergen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String nameKR;
//    private String nameEN;
    private AllergenType allergenType;
}

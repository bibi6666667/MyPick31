package bibi.demo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flavor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameKR;
    private String nameEN;
    private int kcal;
    private boolean isSignature;
    private boolean isDiscontinued;
    private String info;

//    @JsonProperty("image_id")
//    private Long imageId;
    @OneToOne
    private Image image;

    @OneToMany(mappedBy = "flavor")
    private List<FlavorBase> flavorBases = new ArrayList<>();

    @OneToMany(mappedBy = "flavor")
    private List<FlavorTopping> flavorToppings = new ArrayList<>();

    @OneToMany(mappedBy = "flavor")
    private List<FlavorSyrup> flavorSyrups = new ArrayList<>();

    @OneToMany(mappedBy = "flavor")
    private List<FlavorAllergen> flavorAllergens = new ArrayList<>();




}

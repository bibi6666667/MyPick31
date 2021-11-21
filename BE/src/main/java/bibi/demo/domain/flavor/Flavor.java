package bibi.demo.domain.flavor;

import bibi.demo.domain.Image;
import bibi.demo.domain.Info;
import bibi.demo.domain.OnSale;
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
public class Flavor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_kr")
    private String nameKR;
    @Column(name = "name_en")
    private String nameEN;

    private int kcal;

    @Column(name = "is_signature")
    private boolean isSignature;

    @OneToOne
    private Image image;

    @OneToOne
    private Info info;

    @OneToOne
    @JoinColumn(name = "on_sale_id")
    private OnSale onSale;

    @OneToMany(mappedBy = "flavor")
    private List<FlavorBase> flavorBases = new ArrayList<>();

    @OneToMany(mappedBy = "flavor")
    private List<FlavorTopping> flavorToppings = new ArrayList<>();

    @OneToMany(mappedBy = "flavor")
    private List<FlavorSyrup> flavorSyrups = new ArrayList<>();

    @OneToMany(mappedBy = "flavor")
    private List<FlavorAllergen> flavorAllergens = new ArrayList<>();

    public Flavor() {
    }

    public Flavor(Long id, String nameKR, String nameEN, int kcal, boolean isSignature){
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
        this.kcal = kcal;
        this.isSignature = isSignature;
    }
}


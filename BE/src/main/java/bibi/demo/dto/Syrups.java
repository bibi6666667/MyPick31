package bibi.demo.dto;

import bibi.demo.domain.Syrup;
import lombok.Getter;

import java.util.List;

@Getter
public class Syrups {

    private List<Syrup> syrups;

    public Syrups(List<Syrup> syrups) {
        this.syrups = syrups;
    }
}

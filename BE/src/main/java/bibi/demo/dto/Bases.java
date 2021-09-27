package bibi.demo.dto;

import bibi.demo.domain.Base;
import lombok.Getter;

import java.util.List;

@Getter
public class Bases {

    private List<Base> bases;

    public Bases(List<Base> bases) {
        this.bases = bases;
    }
}

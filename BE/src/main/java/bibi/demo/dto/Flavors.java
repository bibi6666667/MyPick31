package bibi.demo.dto;

import bibi.demo.response.FlavorResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class Flavors {

    private List<FlavorResponse> flavorResponses;

    public Flavors(List<FlavorResponse> flavorResponses) {
        this.flavorResponses = flavorResponses;
    }
}

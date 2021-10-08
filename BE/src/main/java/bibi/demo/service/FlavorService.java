package bibi.demo.service;

import bibi.demo.domain.flavor.Flavor;
import bibi.demo.dto.FlavorResponse;
import bibi.demo.repository.FlavorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlavorService {
    private FlavorRepository flavorRepository;

    public FlavorService(FlavorRepository flavorRepository) {
        this.flavorRepository = flavorRepository;
    }

    public List<FlavorResponse> getAllFlavors() {
        List<Flavor> flavors = flavorRepository.findAll();
        return flavorsToFlavorResponses(flavors);
    }

    private List<FlavorResponse> flavorsToFlavorResponses(List<Flavor> flavors) {
        List<FlavorResponse> result = new ArrayList<>();
        for (Flavor flavor : flavors) {
            result.add(FlavorResponse.toFlavorResponse(flavor));
        }
        return result;
    }
}

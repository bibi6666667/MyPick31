package bibi.demo.controller;

import bibi.demo.response.FlavorResponse;
import bibi.demo.service.FlavorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flavor")
public class FlavorController {

    private FlavorService flavorService;

    public FlavorController(FlavorService flavorService) {
        this.flavorService = flavorService;
    }

    @GetMapping()
    public List<FlavorResponse> showAllFlavor() {
        return flavorService.getAllFlavors();
    }

    @GetMapping("/kr/{keywordKR}")
    public List<FlavorResponse> showFlavorByKeywordKR(@PathVariable("keywordKR") String keywordKR) {
        return flavorService.getFlavorsByKeywordKR(keywordKR);
    }

}

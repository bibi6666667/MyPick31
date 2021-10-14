package bibi.demo.controller;

import bibi.demo.response.FlavorResponse;
import bibi.demo.service.FlavorService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/en/{keywordEN}")
    public List<FlavorResponse> showFlavorByKeywordEN(@PathVariable("keywordEN") String keywordEN) {
        return flavorService.getFlavorsByKeywordEN(keywordEN);
    }

    @GetMapping("/order/kr")
    public List<FlavorResponse> showFlavorOrderByNameKR() {
        return flavorService.getFlavorsOrderByNameKR();
    }

    @GetMapping("/order/en")
    public List<FlavorResponse> showFlavorOrderByNameEN() {
        return flavorService.getFlavorsOrderByNameEN();
    }

    @GetMapping("/order/kcal")
    public List<FlavorResponse> showFlavorOrderByKcal() {
        return flavorService.getFlavorsOrderByKcal();
    }

    @GetMapping(params = {"baseType", "toppingType", "syrupType", "allergen"})
    public List<FlavorResponse> showFlavorFiltered(@RequestParam("baseType") String baseType,
                                                   @RequestParam("toppingType") String toppingType,
                                                   @RequestParam("syrupType") String syrupType,
                                                   @RequestParam("allergen") String allergen) {
        return flavorService.getAllFlavorsFiltered(baseType, toppingType, syrupType, allergen);
    }

}

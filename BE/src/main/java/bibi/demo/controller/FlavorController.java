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

    @GetMapping("/signature")
    public List<FlavorResponse> showFlavorSignature() {
        return flavorService.getFlavorsSignature();
    }

    @GetMapping("/sherbet")
    public List<FlavorResponse> showFlavorSherbet() {
        return flavorService.getFlavorsSherbet();
    }

    @GetMapping("/sorbet")
    public List<FlavorResponse> showFlavorSorbet() {
        return flavorService.getFlavorsSorbet();
    }

    @GetMapping("/onSale")
    public List<FlavorResponse> showFlavorOnSale() {
        return flavorService.getFlavorsOnSale();
    }

    @GetMapping(params = {"baseType1", "baseType2", "toppingType1", "toppingType2",
            "syrupType1", "syrupType2", "allergen1", "allergen2"})
    public List<FlavorResponse> showFlavorFiltered(@RequestParam("baseType1") String baseType1,
                                                   @RequestParam("baseType2") String baseType2,
                                                   @RequestParam("toppingType1") String toppingType1,
                                                   @RequestParam("toppingType2") String toppingType2,
                                                   @RequestParam("syrupType1") String syrupType1,
                                                   @RequestParam("syrupType2") String syrupType2,
                                                   @RequestParam("allergen1") String allergen1,
                                                   @RequestParam("allergen2") String allergen2) {
        return flavorService.getAllFlavorsFiltered(baseType1, baseType2, toppingType1, toppingType2,
                syrupType1, syrupType2, allergen1, allergen2);
    }

}

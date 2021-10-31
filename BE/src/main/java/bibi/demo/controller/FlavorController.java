package bibi.demo.controller;

import bibi.demo.response.ApiResponse;
import bibi.demo.response.StatusEnum;
import bibi.demo.response.flavor.FlavorResponse;
import bibi.demo.service.FlavorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flavor")
public class FlavorController {

    private final FlavorService flavorService;

    public FlavorController(FlavorService flavorService) {
        this.flavorService = flavorService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<FlavorResponse>>> showAllFlavor() {
        ApiResponse<List<FlavorResponse>> apiResponse = new ApiResponse<>(StatusEnum.OK, flavorService.getAllFlavors());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/kr/{keywordKR}")
    public ResponseEntity<ApiResponse<List<FlavorResponse>>> showFlavorByKeywordKR(@PathVariable("keywordKR") String keywordKR) {
        ApiResponse<List<FlavorResponse>> apiResponse = new ApiResponse<>(StatusEnum.OK, flavorService.getFlavorsByKeywordKR(keywordKR));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/en/{keywordEN}")
    public ResponseEntity<ApiResponse<List<FlavorResponse>>> showFlavorByKeywordEN(@PathVariable("keywordEN") String keywordEN) {
        ApiResponse<List<FlavorResponse>> apiResponse = new ApiResponse<>(StatusEnum.OK, flavorService.getFlavorsByKeywordEN(keywordEN));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/order/kr")
    public ResponseEntity<ApiResponse<List<FlavorResponse>>> showFlavorOrderByNameKR() {
        ApiResponse<List<FlavorResponse>> apiResponse = new ApiResponse<>(StatusEnum.OK, flavorService.getFlavorsOrderByNameKR());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/order/en")
    public ResponseEntity<ApiResponse<List<FlavorResponse>>> showFlavorOrderByNameEN() {
        ApiResponse<List<FlavorResponse>> apiResponse = new ApiResponse<>(StatusEnum.OK, flavorService.getFlavorsOrderByNameEN());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/order/kcal")
    public ResponseEntity<ApiResponse<List<FlavorResponse>>> showFlavorOrderByKcal() {
        ApiResponse<List<FlavorResponse>> apiResponse = new ApiResponse<>(StatusEnum.OK, flavorService.getFlavorsOrderByKcal());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/signature")
    public ResponseEntity<ApiResponse<List<FlavorResponse>>> showFlavorSignature() {
        ApiResponse<List<FlavorResponse>> apiResponse = new ApiResponse<>(StatusEnum.OK, flavorService.getFlavorsSignature());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/sherbet")
    public ResponseEntity<ApiResponse<List<FlavorResponse>>> showFlavorSherbet() {
        ApiResponse<List<FlavorResponse>> apiResponse = new ApiResponse<>(StatusEnum.OK, flavorService.getFlavorsSherbet());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/sorbet")
    public ResponseEntity<ApiResponse<List<FlavorResponse>>> showFlavorSorbet() {
        ApiResponse<List<FlavorResponse>> apiResponse = new ApiResponse<>(StatusEnum.OK, flavorService.getFlavorsSorbet());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/on-sale")
    public ResponseEntity<ApiResponse<List<FlavorResponse>>> showFlavorOnSale() {
        ApiResponse<List<FlavorResponse>> apiResponse = new ApiResponse<>(StatusEnum.OK, flavorService.getFlavorsOnSale());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(params = {"base-type1", "base-type2", "topping-type1", "topping-type2",
            "syrup-type1", "syrup-type2", "allergen1", "allergen2"})
    public ResponseEntity<ApiResponse<List<FlavorResponse>>> showFlavorFiltered(@RequestParam("base-type1") String baseType1,
                                                   @RequestParam("base-type2") String baseType2,
                                                   @RequestParam("topping-type1") String toppingType1,
                                                   @RequestParam("topping-type2") String toppingType2,
                                                   @RequestParam("syrup-type1") String syrupType1,
                                                   @RequestParam("syrup-type2") String syrupType2,
                                                   @RequestParam("allergen1") String allergen1,
                                                   @RequestParam("allergen2") String allergen2) {
        ApiResponse<List<FlavorResponse>> apiResponse = new ApiResponse<>(StatusEnum.OK, flavorService.getAllFlavorsFiltered(baseType1, baseType2,
                toppingType1, toppingType2, syrupType1, syrupType2, allergen1, allergen2));
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

}
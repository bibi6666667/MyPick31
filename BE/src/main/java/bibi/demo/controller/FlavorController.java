package bibi.demo.controller;

import bibi.demo.response.ApiResponse;
import bibi.demo.response.StatusEnum;
import bibi.demo.service.FlavorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flavor")
public class FlavorController {

    private final FlavorService flavorService;

    public FlavorController(FlavorService flavorService) {
        this.flavorService = flavorService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse> showAllFlavor() {
        ApiResponse apiResponse = new ApiResponse(StatusEnum.OK, flavorService.getAllFlavors());
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/kr/{keywordKR}")
    public ResponseEntity<ApiResponse> showFlavorByKeywordKR(@PathVariable("keywordKR") String keywordKR) {
        ApiResponse apiResponse = new ApiResponse(StatusEnum.OK, flavorService.getFlavorsByKeywordKR(keywordKR));
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/en/{keywordEN}")
    public ResponseEntity<ApiResponse> showFlavorByKeywordEN(@PathVariable("keywordEN") String keywordEN) {
        ApiResponse apiResponse = new ApiResponse(StatusEnum.OK, flavorService.getFlavorsByKeywordEN(keywordEN));
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/order/kr")
    public ResponseEntity<ApiResponse> showFlavorOrderByNameKR() {
        ApiResponse apiResponse = new ApiResponse(StatusEnum.OK, flavorService.getFlavorsOrderByNameKR());
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/order/en")
    public ResponseEntity<ApiResponse> showFlavorOrderByNameEN() {
        ApiResponse apiResponse = new ApiResponse(StatusEnum.OK, flavorService.getFlavorsOrderByNameEN());
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/order/kcal")
    public ResponseEntity<ApiResponse> showFlavorOrderByKcal() {
        ApiResponse apiResponse = new ApiResponse(StatusEnum.OK, flavorService.getFlavorsOrderByKcal());
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/signature")
    public ResponseEntity<ApiResponse> showFlavorSignature() {
        ApiResponse apiResponse = new ApiResponse(StatusEnum.OK, flavorService.getFlavorsSignature());
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/sherbet")
    public ResponseEntity<ApiResponse> showFlavorSherbet() {
        ApiResponse apiResponse = new ApiResponse(StatusEnum.OK, flavorService.getFlavorsSherbet());
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/sorbet")
    public ResponseEntity<ApiResponse> showFlavorSorbet() {
        ApiResponse apiResponse = new ApiResponse(StatusEnum.OK, flavorService.getFlavorsSorbet());
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/on-sale")
    public ResponseEntity<ApiResponse> showFlavorOnSale() {
        ApiResponse apiResponse = new ApiResponse(StatusEnum.OK, flavorService.getFlavorsOnSale());
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @GetMapping(params = {"base-type1", "base-type2", "topping-type1", "topping-type2",
            "syrup-type1", "syrup-type2", "allergen1", "allergen2"})
    public ResponseEntity<ApiResponse> showFlavorFiltered(@RequestParam("base-type1") String baseType1,
                                                   @RequestParam("base-type2") String baseType2,
                                                   @RequestParam("topping-type1") String toppingType1,
                                                   @RequestParam("topping-type2") String toppingType2,
                                                   @RequestParam("syrup-type1") String syrupType1,
                                                   @RequestParam("syrup-type2") String syrupType2,
                                                   @RequestParam("allergen1") String allergen1,
                                                   @RequestParam("allergen2") String allergen2) {
        ApiResponse apiResponse = new ApiResponse(StatusEnum.OK, flavorService.getAllFlavorsFiltered(baseType1, baseType2,
                toppingType1, toppingType2, syrupType1, syrupType2, allergen1, allergen2));
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

}
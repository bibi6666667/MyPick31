package bibi.demo.service;

import bibi.demo.domain.Allergen;
import bibi.demo.domain.Base;
import bibi.demo.domain.Syrup;
import bibi.demo.domain.Topping;
import bibi.demo.domain.flavor.*;
import bibi.demo.repository.*;
import bibi.demo.response.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlavorService {

    private final FlavorRepository flavorRepository;
    private final BaseRepository baseRepository;
    private final ToppingRepository toppingRepository;
    private final SyrupRepository syrupRepository;
    private final AllergenRepository allergenRepository;

    public FlavorService(FlavorRepository flavorRepository,
                         BaseRepository baseRepository,
                         ToppingRepository toppingRepository,
                         SyrupRepository syrupRepository,
                         AllergenRepository allergenRepository) {
        this.flavorRepository = flavorRepository;
        this.baseRepository = baseRepository;
        this.toppingRepository = toppingRepository;
        this.syrupRepository = syrupRepository;
        this.allergenRepository = allergenRepository;
    }

    public List<FlavorResponse> getAllFlavors() {
        List<Flavor> flavors = flavorRepository.findAll();
        return flavorsToFlavorResponses(flavors);
    }

    private List<FlavorResponse> flavorsToFlavorResponses(List<Flavor> flavors) {
        List<FlavorResponse> result = new ArrayList<>();
        for (Flavor flavor : flavors) {
            result.add(flavorToFlavorResponse(flavor));
        }
        return result;
    }

    private FlavorResponse flavorToFlavorResponse(Flavor flavor) {
        return new FlavorResponse(flavor.getId(), flavor.getNameKR(), flavor.getNameEN(), flavor.getKcal(),
                flavor.isSignature(), flavor.getInfo(), flavor.getImage(), flavor.getOnSale(),
                flavorBasesToBaseResponses(flavor.getFlavorBases()),
                flavorToppingsToToppingResponses(flavor.getFlavorToppings()),
                flavorSyrupsToSyrupResponses(flavor.getFlavorSyrups()),
                flavorAllergensToAllergenResponses(flavor.getFlavorAllergens()));
    }

    private List<BaseResponse> flavorBasesToBaseResponses(List<FlavorBase> flavorBases) {
        List<BaseResponse> result = new ArrayList<>();
        for (FlavorBase flavorBase : flavorBases) {
            Base base = baseRepository.findBaseById(flavorBase.getBase().getId());
            result.add(BaseResponse.toBaseResponse(base));
        }
        return result;
    }

    private List<ToppingResponse> flavorToppingsToToppingResponses(List<FlavorTopping> flavorToppings) {
        List<ToppingResponse> result = new ArrayList<>();
        for (FlavorTopping flavorTopping : flavorToppings) {
            Topping topping = toppingRepository.findToppingById(flavorTopping.getTopping().getId());
            result.add(ToppingResponse.toToppingResponse(topping));
        }
        return result;
    }

    private List<SyrupResponse> flavorSyrupsToSyrupResponses(List<FlavorSyrup> flavorSyrups) {
        List<SyrupResponse> result = new ArrayList<>();
        for (FlavorSyrup flavorSyrup : flavorSyrups) {
            Syrup syrup = syrupRepository.findSyrupById(flavorSyrup.getSyrup().getId());
            result.add(SyrupResponse.toSyrupResponse(syrup));
        }
        return result;
    }

    private List<AllergenResponse> flavorAllergensToAllergenResponses(List<FlavorAllergen> flavorAllergens) {
        List<AllergenResponse> result = new ArrayList<>();
        for (FlavorAllergen flavorAllergen : flavorAllergens) {
            Allergen allergen = allergenRepository.findAllergenById(flavorAllergen.getAllergen().getId());
            result.add(AllergenResponse.toAllergenResponse(allergen));
        }
        return result;
    }
}

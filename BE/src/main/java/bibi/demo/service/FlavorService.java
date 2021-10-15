package bibi.demo.service;

import bibi.demo.domain.Allergen;
import bibi.demo.domain.Base;
import bibi.demo.domain.Syrup;
import bibi.demo.domain.Topping;
import bibi.demo.domain.flavor.*;
import bibi.demo.repository.*;
import bibi.demo.repository.type.BaseTypeRepository;
import bibi.demo.repository.type.SyrupTypeRepository;
import bibi.demo.repository.type.ToppingTypeRepository;
import bibi.demo.response.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FlavorService {

    private final FlavorRepository flavorRepository;
    private final BaseRepository baseRepository;
    private final ToppingRepository toppingRepository;
    private final SyrupRepository syrupRepository;
    private final AllergenRepository allergenRepository;
    private final FlavorBaseRepository flavorBaseRepository;
    private final FlavorToppingRepository flavorToppingRepository;
    private final FlavorSyrupRepository flavorSyrupRepository;
    private final FlavorAllergenRepository flavorAllergenRepository;
    private final BaseTypeRepository baseTypeRepository;
    private final ToppingTypeRepository toppingTypeRepository;
    private final SyrupTypeRepository syrupTypeRepository;

    public FlavorService(FlavorRepository flavorRepository,
                         BaseRepository baseRepository,
                         ToppingRepository toppingRepository,
                         SyrupRepository syrupRepository,
                         AllergenRepository allergenRepository,
                         FlavorBaseRepository flavorBaseRepository,
                         FlavorToppingRepository flavorToppingRepository,
                         FlavorSyrupRepository flavorSyrupRepository,
                         FlavorAllergenRepository flavorAllergenRepository,
                         BaseTypeRepository baseTypeRepository,
                         ToppingTypeRepository toppingTypeRepository,
                         SyrupTypeRepository syrupTypeRepository) {
        this.flavorRepository = flavorRepository;
        this.baseRepository = baseRepository;
        this.toppingRepository = toppingRepository;
        this.syrupRepository = syrupRepository;
        this.allergenRepository = allergenRepository;
        this.flavorBaseRepository = flavorBaseRepository;
        this.flavorToppingRepository = flavorToppingRepository;
        this.flavorSyrupRepository = flavorSyrupRepository;
        this.flavorAllergenRepository = flavorAllergenRepository;
        this.baseTypeRepository = baseTypeRepository;
        this.toppingTypeRepository = toppingTypeRepository;
        this.syrupTypeRepository = syrupTypeRepository;
    }

    public List<FlavorResponse> getAllFlavors() {
        List<Flavor> flavors = flavorRepository.findAll();
        return flavorsToFlavorResponses(flavors);
    }

    public List<FlavorResponse> getFlavorsByKeywordKR(String keywordKR) {
        List<Flavor> flavors = flavorRepository.findByNameKRContaining(keywordKR);
        return flavorsToFlavorResponses(flavors);
    }

    public List<FlavorResponse> getFlavorsByKeywordEN(String keywordEN) {
        List<Flavor> flavors = flavorRepository.findByNameENContainingIgnoreCase(keywordEN);
        return flavorsToFlavorResponses(flavors);
    }

    public List<FlavorResponse> getFlavorsOrderByNameKR() {
        List<Flavor> flavors = flavorRepository.findAll(Sort.by("nameKR"));
        return flavorsToFlavorResponses(flavors);
    }

    public List<FlavorResponse> getFlavorsOrderByNameEN() {
        List<Flavor> flavors = flavorRepository.findAll(Sort.by("nameEN"));
        return flavorsToFlavorResponses(flavors);
    }

    public List<FlavorResponse> getFlavorsOrderByKcal() {
        List<Flavor> flavors = flavorRepository.findAll(Sort.by("kcal"));
        return flavorsToFlavorResponses(flavors);
    }

    public List<FlavorResponse> getAllFlavorsFiltered(String baseType1, String baseType2, String toppingType1, String toppingType2,
                                                      String syrupType1, String syrupType2, String allergen1, String allergen2) {
        // TODO : 타입1이 비어 있고 타입2만 있을 때 예외 발생시키기

        if (baseType1.equals("") && baseType2.equals("") && toppingType1.equals("") && toppingType2.equals("")
        && syrupType1.equals("") && syrupType2.equals("") && allergen1.equals("") && allergen2.equals("")) {
            getAllFlavors();
        }

        List<Flavor> result = new ArrayList<>();
        List<Flavor> flavorsFilteredByBaseType = getFlavorsFilteredByBaseType(baseType1);
        List<Flavor> flavorsFilteredByBaseType2 = getFlavorsFilteredByBaseType(baseType2);
        List<Flavor> flavorsFilteredByToppingType = getFlavorsFilteredByToppingType(toppingType1);
        List<Flavor> flavorsFilteredByToppingType2 = getFlavorsFilteredByToppingType(toppingType2);
        List<Flavor> flavorsFilteredBySyrupType = getFlavorsFilteredBySyrupType(syrupType1);
        List<Flavor> flavorsFilteredBySyrupType2 = getFlavorsFilteredBySyrupType(syrupType2);
        List<Flavor> flavorsFilteredByAllergen = getFlavorsFilteredByAllergen(allergen1);
        List<Flavor> flavorsFilteredByAllergen2 = getFlavorsFilteredByAllergen(allergen2);

        // 타입끼리의 교집합 구하기
        if (!baseType1.equals("") && !baseType2.equals("")) {
            flavorsFilteredByBaseType.retainAll(flavorsFilteredByBaseType2);
        }
        if (!toppingType1.equals("") && !toppingType2.equals("")) {
            flavorsFilteredByToppingType.retainAll(flavorsFilteredByToppingType2);
        }
        if (!syrupType1.equals("") && !syrupType2.equals("")) {
            flavorsFilteredBySyrupType.retainAll(flavorsFilteredBySyrupType2);
        }
        if (!allergen1.equals("") && !allergen2.equals("")) {
            flavorsFilteredByAllergen.retainAll(flavorsFilteredByAllergen2);
        }

        // i) 베이스타입/토핑타입/시럽타입/알러전 중 한 개만 입력
        // ii) 베이스타입/토핑타입/시럽타입/알러전 중 두 개 입력
        // iii) 베이스타입/토핑타입/시럽타입/알러전 중 세 개 입력


        // iv) 베이스타입/토핑타입/시럽타입/알러전 모두 입력
        if (!baseType1.equals("") && !baseType2.equals("") && !toppingType1.equals("") && !toppingType2.equals("")
                && !syrupType1.equals("") && !syrupType2.equals("") && !allergen1.equals("") && !allergen2.equals("")) {
            flavorsFilteredByBaseType.retainAll(flavorsFilteredByToppingType);
            flavorsFilteredByBaseType.retainAll(flavorsFilteredBySyrupType);
            flavorsFilteredByBaseType.retainAll(flavorsFilteredByAllergen);
            result.addAll(flavorsFilteredByBaseType);
        }
        flavorsFilteredByBaseType.retainAll(flavorsFilteredByToppingType);
        flavorsFilteredByBaseType.retainAll(flavorsFilteredBySyrupType);
        flavorsFilteredByBaseType.retainAll(flavorsFilteredByAllergen);
        result.addAll(flavorsFilteredByBaseType);

        // TODO : 판매 중인 플레이버를 앞쪽으로 정렬
//        result.addAll(flavorsFilteredByBaseType);
//        result.addAll(flavorsFilteredByToppingType);
//        result.addAll(flavorsFilteredBySyrupType);
//        result.addAll(flavorsFilteredByAllergen);
        return flavorsToFlavorResponses(result);
    }

    private List<Flavor> getFlavorsFilteredByBaseType(String baseType) {
        List<Flavor> flavors = new ArrayList<>();
        if (baseType.equals("")) return flavors;

        // 그 베이스 타입의 베이스타입ID 찾기
        Long baseTypeId = baseTypeRepository.findIdByNameKR(baseType).orElseThrow(NoSuchElementException::new);
        // 그 베이스타입ID를 갖는 베이스들 찾기
        List<Base> bases = baseRepository.findByBaseTypeId(baseTypeId);

        // 그 베이스들을 갖는 플레이버베이스 찾기
        List<FlavorBase> flavorBases = new ArrayList<>();
        for (Base base : bases) {
            List<FlavorBase> flavorBasesByBaseId = flavorBaseRepository.findFlavorBasesByBaseId(base.getId());
            flavorBases.addAll(flavorBasesByBaseId);
        }

        // 플레이버베이스로 그 베이스들을 갖는 플레이버 찾기
        for (FlavorBase flavorBase : flavorBases) {
            Flavor flavor = flavorRepository.findById(flavorBase.getFlavor().getId()).orElseThrow(NoSuchElementException::new);
            if (!flavors.contains(flavor)) { // 중복 제거
                System.out.println(flavorBase.getBase().getNameKR() + "를 베이스로 갖는 플레이버 " + flavor.getNameKR());
                flavors.add(flavor);
            }
        }
        return flavors;
    }

    private List<Flavor> getFlavorsFilteredByToppingType(String toppingType) {
        List<Flavor> flavors = new ArrayList<>();
        if (toppingType.equals("")) return flavors;

        Long toppingTypeId = toppingTypeRepository.findIdByNameKR(toppingType).orElseThrow(NoSuchElementException::new);
        List<Topping> toppings = toppingRepository.findByToppingTypeId(toppingTypeId);

        List<FlavorTopping> flavorToppings = new ArrayList<>();
        for (Topping topping : toppings) {
            List<FlavorTopping> flavorToppingsByToppingId = flavorToppingRepository.findByToppingId(topping.getId());
            flavorToppings.addAll(flavorToppingsByToppingId);
        }

        for (FlavorTopping flavorTopping : flavorToppings) {
            Flavor flavor = flavorRepository.findById(flavorTopping.getFlavor().getId()).orElseThrow(NoSuchElementException::new);
            if (!flavors.contains(flavor)) {
                System.out.println(flavorTopping.getTopping().getNameKR() + "를 토핑으로 갖는 플레이버 " + flavor.getNameKR());
                flavors.add(flavor);
            }
        }
        return flavors;
    }

    private List<Flavor> getFlavorsFilteredBySyrupType(String syrupType) {
        List<Flavor> flavors = new ArrayList<>();
        if (syrupType.equals("")) return flavors;

        Long syrupTypeId = syrupTypeRepository.findIdByNameKR(syrupType).orElseThrow(NoSuchElementException::new);
        List<Syrup> syrups = syrupRepository.findBySyrupTypeId(syrupTypeId);

        List<FlavorSyrup> flavorSyrups = new ArrayList<>();
        for (Syrup syrup : syrups) {
            List<FlavorSyrup> flavorSyrupsBySyrupId = flavorSyrupRepository.findBySyrupId(syrup.getId());
            flavorSyrups.addAll(flavorSyrupsBySyrupId);
        }

        for (FlavorSyrup flavorSyrup : flavorSyrups) {
            Flavor flavor = flavorRepository.findById(flavorSyrup.getFlavor().getId()).orElseThrow(NoSuchElementException::new);
            if (!flavors.contains(flavor)) {
                System.out.println(flavorSyrup.getSyrup().getNameKR() + "를 시럽으로 갖는 플레이버 " + flavor.getNameKR());
                flavors.add(flavor);
            }
        }
        return flavors;
    }

    private List<Flavor> getFlavorsFilteredByAllergen(String allergen) {
        List<Flavor> flavors = new ArrayList<>();
        if (allergen.equals("")) return flavors;

        Long allergenId = allergenRepository.findIdByNameKR(allergen).orElseThrow(NoSuchElementException::new);

        List<FlavorAllergen> flavorAllergens = flavorAllergenRepository.findByAllergenId(allergenId);

        for (FlavorAllergen flavorAllergen : flavorAllergens) {
            Flavor flavor = flavorRepository.findById(flavorAllergen.getFlavor().getId()).orElseThrow(NoSuchElementException::new);
            if (!flavors.contains(flavor)) {
                System.out.println(flavorAllergen.getAllergen().getNameKR() + " 알레르기 성분을 갖는 플레이버 " + flavor.getNameKR());
                flavors.add(flavor);
            }
        }
        return flavors;
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
            Base base = baseRepository.findById(flavorBase.getBase().getId()).orElseThrow(NoSuchElementException::new);
            result.add(BaseResponse.toBaseResponse(base));
        }
        return result;
    }

    private List<ToppingResponse> flavorToppingsToToppingResponses(List<FlavorTopping> flavorToppings) {
        List<ToppingResponse> result = new ArrayList<>();
        for (FlavorTopping flavorTopping : flavorToppings) {
            Topping topping = toppingRepository.findById(flavorTopping.getTopping().getId()).orElseThrow(NoSuchElementException::new);
            result.add(ToppingResponse.toToppingResponse(topping));
        }
        return result;
    }

    private List<SyrupResponse> flavorSyrupsToSyrupResponses(List<FlavorSyrup> flavorSyrups) {
        List<SyrupResponse> result = new ArrayList<>();
        for (FlavorSyrup flavorSyrup : flavorSyrups) {
            Syrup syrup = syrupRepository.findById(flavorSyrup.getSyrup().getId()).orElseThrow(NoSuchElementException::new);
            result.add(SyrupResponse.toSyrupResponse(syrup));
        }
        return result;
    }

    private List<AllergenResponse> flavorAllergensToAllergenResponses(List<FlavorAllergen> flavorAllergens) {
        List<AllergenResponse> result = new ArrayList<>();
        for (FlavorAllergen flavorAllergen : flavorAllergens) {
            Allergen allergen = allergenRepository.findById(flavorAllergen.getAllergen().getId()).orElseThrow(NoSuchElementException::new);
            result.add(AllergenResponse.toAllergenResponse(allergen));
        }
        return result;
    }
}

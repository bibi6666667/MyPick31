package bibi.demo.service;

import bibi.demo.domain.Allergen;
import bibi.demo.domain.Base;
import bibi.demo.domain.Syrup;
import bibi.demo.domain.Topping;
import bibi.demo.domain.flavor.*;
import bibi.demo.repository.*;
import bibi.demo.repository.type.BaseTypeRepository;
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
    private final BaseTypeRepository baseTypeRepository;

    public FlavorService(FlavorRepository flavorRepository,
                         BaseRepository baseRepository,
                         ToppingRepository toppingRepository,
                         SyrupRepository syrupRepository,
                         AllergenRepository allergenRepository,
                         FlavorBaseRepository flavorBaseRepository,
                         BaseTypeRepository baseTypeRepository) {
        this.flavorRepository = flavorRepository;
        this.baseRepository = baseRepository;
        this.toppingRepository = toppingRepository;
        this.syrupRepository = syrupRepository;
        this.allergenRepository = allergenRepository;
        this.flavorBaseRepository = flavorBaseRepository;
        this.baseTypeRepository = baseTypeRepository;
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

    public List<FlavorResponse> getAllFlavorsFiltered(String baseType, String toppingType, String syrupType, String allergenType) {

        //System.out.println(baseType.equals("")); - 아무 파라미터 없이 검색할 때
        List<Flavor> flavors = new ArrayList<>();
        List<Flavor> flavorsFilteredByBaseType = getFlavorsFilteredByBaseType(baseType);
        // 베이스 토핑 시럽 알러전 중 선택된 그룹 모두의 "교집합"만 결과로 보내야 함
        flavors.addAll(flavorsFilteredByBaseType);
        return flavorsToFlavorResponses(flavors);
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

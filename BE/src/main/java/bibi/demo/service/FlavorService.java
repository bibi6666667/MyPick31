package bibi.demo.service;

import bibi.demo.domain.*;
import bibi.demo.domain.flavor.*;
import bibi.demo.exception.NoSuchFlavorException;
import bibi.demo.exception.NoSuchTypeException;
import bibi.demo.exception.TypeInputException;
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
        return flavorsToFlavorResponses(orderFlavorsByOnSale(flavors));
    }

    public List<FlavorResponse> getFlavorsByKeywordKR(String keywordKR) {
        List<Flavor> flavors = flavorRepository.findByNameKRContaining(keywordKR);
        return flavorsToFlavorResponses(orderFlavorsByOnSale(flavors));
    }

    public List<FlavorResponse> getFlavorsByKeywordEN(String keywordEN) {
        List<Flavor> flavors = flavorRepository.findByNameENContainingIgnoreCase(keywordEN);
        return flavorsToFlavorResponses(orderFlavorsByOnSale(flavors));
    }

    public List<FlavorResponse> getFlavorsOrderByNameKR() {
        List<Flavor> flavors = flavorRepository.findAll(Sort.by("nameKR"));
        return flavorsToFlavorResponses(orderFlavorsByOnSale(flavors));
    }

    public List<FlavorResponse> getFlavorsOrderByNameEN() {
        List<Flavor> flavors = flavorRepository.findAll(Sort.by("nameEN"));
        return flavorsToFlavorResponses(orderFlavorsByOnSale(flavors));
    }

    public List<FlavorResponse> getFlavorsOrderByKcal() {
        List<Flavor> flavors = flavorRepository.findAll(Sort.by("kcal"));
        return flavorsToFlavorResponses(orderFlavorsByOnSale(flavors));
    }

    public List<FlavorResponse> getAllFlavorsFiltered(String baseType1, String baseType2, String toppingType1, String toppingType2,
                                                      String syrupType1, String syrupType2, String allergen1, String allergen2) {
        if (baseType1.equals("") && !baseType2.equals("") || toppingType1.equals("") && !toppingType2.equals("")
                || syrupType1.equals("") && !syrupType2.equals("") || allergen1.equals("") && !allergen2.equals("")) {
            throw new TypeInputException();
        }

        // 아무것도 입력하지 않은 경우
        if (baseType1.equals("") && baseType2.equals("") && toppingType1.equals("") && toppingType2.equals("")
                && syrupType1.equals("") && syrupType2.equals("") && allergen1.equals("") && allergen2.equals("")) {
            return getAllFlavors();
        }

        // 타입2까지 지정된 경우, 타입1&타입2 끼리의 교집합 구하기
        List<Flavor> flavorsFilteredByBaseType = getFlavorsFilteredByBaseType(baseType1);
        if (!baseType1.equals("") && !baseType2.equals("")) {
            flavorsFilteredByBaseType = getIntersectionOfTwoSet(flavorsFilteredByBaseType,
                    getFlavorsFilteredByBaseType(baseType2));
        }
        List<Flavor> flavorsFilteredByToppingType = getFlavorsFilteredByToppingType(toppingType1);
        if (!toppingType1.equals("") && !toppingType2.equals("")) {
            flavorsFilteredByToppingType = getIntersectionOfTwoSet(flavorsFilteredByToppingType,
                    getFlavorsFilteredByToppingType(toppingType2));
        }
        List<Flavor> flavorsFilteredBySyrupType = getFlavorsFilteredBySyrupType(syrupType1);
        if (!syrupType1.equals("") && !syrupType2.equals("")) {
            flavorsFilteredBySyrupType = getIntersectionOfTwoSet(flavorsFilteredBySyrupType,
                    getFlavorsFilteredBySyrupType(syrupType2));
        }
        List<Flavor> flavorsFilteredByAllergen = getFlavorsFilteredByAllergen(allergen1);
        if (!allergen1.equals("") && !allergen2.equals("")) {
            flavorsFilteredByAllergen = getIntersectionOfTwoSet(flavorsFilteredByAllergen,
                    getFlavorsFilteredByAllergen(allergen2));
        }

        List<Flavor> filteredFlavorList = new ArrayList<>();
        boolean isBaseTypeEmpty = flavorsFilteredByBaseType.isEmpty();
        boolean isToppingTypeEmpty = flavorsFilteredByToppingType.isEmpty();
        boolean isSyrupTypeEmpty = flavorsFilteredBySyrupType.isEmpty();
        boolean isAllergenTypeEmpty = flavorsFilteredByAllergen.isEmpty();

        // i) 베이스타입/토핑타입/시럽타입/알러전 중 한 개만 입력
        if (!isBaseTypeEmpty && isToppingTypeEmpty && isSyrupTypeEmpty && isAllergenTypeEmpty) { // 베이스
            filteredFlavorList.addAll(flavorsFilteredByBaseType);
        }
        if (isBaseTypeEmpty && !isToppingTypeEmpty && isSyrupTypeEmpty && isAllergenTypeEmpty) { // 토핑
            filteredFlavorList.addAll(flavorsFilteredByToppingType);
        }
        if (isBaseTypeEmpty && isToppingTypeEmpty && !isSyrupTypeEmpty && isAllergenTypeEmpty) { // 시럽
            filteredFlavorList.addAll(flavorsFilteredBySyrupType);
        }
        if (isBaseTypeEmpty && isToppingTypeEmpty && isSyrupTypeEmpty && !isAllergenTypeEmpty) { // 알러전
            filteredFlavorList.addAll(flavorsFilteredByAllergen);
        }
        // ii) 베이스타입/토핑타입/시럽타입/알러전 중 두 개 입력
        if (!isBaseTypeEmpty && !isToppingTypeEmpty && isSyrupTypeEmpty && isAllergenTypeEmpty) { // 베이스, 토핑
            List<Flavor> baseFilteredFlavors = new ArrayList<>(flavorsFilteredByBaseType);
            baseFilteredFlavors = getIntersectionOfTwoSet(baseFilteredFlavors, flavorsFilteredByToppingType);
            filteredFlavorList.addAll(baseFilteredFlavors);
        }
        if (!isBaseTypeEmpty && isToppingTypeEmpty && !isSyrupTypeEmpty && isAllergenTypeEmpty) { // 베이스, 시럽
            List<Flavor> baseFilteredFlavors = new ArrayList<>(flavorsFilteredByBaseType);
            baseFilteredFlavors = getIntersectionOfTwoSet(baseFilteredFlavors, flavorsFilteredBySyrupType);
            filteredFlavorList.addAll(baseFilteredFlavors);
        }
        if (!isBaseTypeEmpty && isToppingTypeEmpty && isSyrupTypeEmpty && !isAllergenTypeEmpty) { // 베이스, 알러전
            List<Flavor> baseFilteredFlavors = new ArrayList<>(flavorsFilteredByBaseType);
            baseFilteredFlavors = getIntersectionOfTwoSet(baseFilteredFlavors, flavorsFilteredByAllergen);
            filteredFlavorList.addAll(baseFilteredFlavors);
        }
        if (isBaseTypeEmpty && !isToppingTypeEmpty && !isSyrupTypeEmpty && isAllergenTypeEmpty) { // 토핑, 시럽
            List<Flavor> toppingFilteredFlavors = new ArrayList<>(flavorsFilteredByToppingType);
            toppingFilteredFlavors = getIntersectionOfTwoSet(toppingFilteredFlavors, flavorsFilteredBySyrupType);
            filteredFlavorList.addAll(toppingFilteredFlavors);
        }
        if (isBaseTypeEmpty && !isToppingTypeEmpty && isSyrupTypeEmpty && !isAllergenTypeEmpty) { // 토핑, 알러지
            List<Flavor> toppingFilteredFlavors = new ArrayList<>(flavorsFilteredByToppingType);
            toppingFilteredFlavors = getIntersectionOfTwoSet(toppingFilteredFlavors, flavorsFilteredByAllergen);
            filteredFlavorList.addAll(toppingFilteredFlavors);
        }
        if (isBaseTypeEmpty && isToppingTypeEmpty && !isSyrupTypeEmpty && !isAllergenTypeEmpty) { // 시럽, 알러지
            List<Flavor> syrupFilteredFlavors = new ArrayList<>(flavorsFilteredBySyrupType);
            syrupFilteredFlavors = getIntersectionOfTwoSet(syrupFilteredFlavors, flavorsFilteredByAllergen);
            filteredFlavorList.addAll(syrupFilteredFlavors);
        }
        // iii) 베이스타입/토핑타입/시럽타입/알러전 중 세 개 입력
        if (!isBaseTypeEmpty && !isToppingTypeEmpty && !isSyrupTypeEmpty && isAllergenTypeEmpty) { // 베이스 토핑 시럽
            List<Flavor> baseFilteredFlavors = new ArrayList<>(flavorsFilteredByBaseType);
            baseFilteredFlavors = getIntersectionOfThreeSet(baseFilteredFlavors, flavorsFilteredByToppingType, flavorsFilteredBySyrupType);
            filteredFlavorList.addAll(baseFilteredFlavors);
        }
        if (!isBaseTypeEmpty && !isToppingTypeEmpty && isSyrupTypeEmpty && !isAllergenTypeEmpty) { // 베이스 토핑 알러지
            List<Flavor> baseFilteredFlavors = new ArrayList<>(flavorsFilteredByBaseType);
            baseFilteredFlavors = getIntersectionOfThreeSet(baseFilteredFlavors, flavorsFilteredByToppingType, flavorsFilteredByAllergen);
            filteredFlavorList.addAll(baseFilteredFlavors);
        }
        if (!isBaseTypeEmpty && isToppingTypeEmpty && !isSyrupTypeEmpty && !isAllergenTypeEmpty) { // 베이스 시럽 알러지
            List<Flavor> baseFilteredFlavors = new ArrayList<>(flavorsFilteredByBaseType);
            baseFilteredFlavors = getIntersectionOfThreeSet(baseFilteredFlavors, flavorsFilteredBySyrupType, flavorsFilteredByAllergen);
            filteredFlavorList.addAll(baseFilteredFlavors);
        }
        if (isBaseTypeEmpty && !isToppingTypeEmpty && !isSyrupTypeEmpty && !isAllergenTypeEmpty) { // 토핑 시럽 알러지
            List<Flavor> toppingFilteredFlavors = new ArrayList<>(flavorsFilteredByToppingType);
            toppingFilteredFlavors = getIntersectionOfThreeSet(toppingFilteredFlavors, flavorsFilteredBySyrupType, flavorsFilteredByAllergen);
            filteredFlavorList.addAll(toppingFilteredFlavors);
        }
        // iv) 베이스타입/토핑타입/시럽타입/알러전 모두 입력
        if (!isBaseTypeEmpty && !isToppingTypeEmpty && !isSyrupTypeEmpty && !isAllergenTypeEmpty) { // 베이스 토핑 시럽 알러지
            List<Flavor> baseFilteredFlavors = new ArrayList<>(flavorsFilteredByBaseType);
            baseFilteredFlavors = getIntersectionOfFourSet(baseFilteredFlavors, flavorsFilteredByToppingType,
                    flavorsFilteredBySyrupType, flavorsFilteredByAllergen);
            filteredFlavorList.addAll(baseFilteredFlavors);
        }

        return flavorsToFlavorResponses(orderFlavorsByOnSale(filteredFlavorList));
    }

    private <T> List<T> getIntersectionOfTwoSet(List<T> set1, List<T> set2) {
        set1.retainAll(set2);
        return set1;
    }

    private <T> List<T> getIntersectionOfThreeSet(List<T> set1, List<T> set2, List<T> set3) {
        set1.retainAll(set2);
        set1.retainAll(set3);
        return set1;
    }

    private <T> List<T> getIntersectionOfFourSet(List<T> set1, List<T> set2, List<T> set3, List<T> set4) {
        set1.retainAll(set2);
        set1.retainAll(set3);
        set1.retainAll(set4);
        return set1;
    }

    private List<Flavor> getFlavorsFilteredByBaseType(String baseType) {
        List<Flavor> flavors = new ArrayList<>();
        if (baseType.equals("")) return flavors;

        // 그 베이스 타입의 베이스타입ID 찾기
        Long baseTypeId = baseTypeRepository.findIdByNameKR(baseType).orElseThrow(NoSuchTypeException::new);
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
            Flavor flavor = flavorRepository.findById(flavorBase.getFlavor().getId()).orElseThrow(NoSuchFlavorException::new);
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

        Long toppingTypeId = toppingTypeRepository.findIdByNameKR(toppingType).orElseThrow(NoSuchTypeException::new);
        List<Topping> toppings = toppingRepository.findByToppingTypeId(toppingTypeId);

        List<FlavorTopping> flavorToppings = new ArrayList<>();
        for (Topping topping : toppings) {
            List<FlavorTopping> flavorToppingsByToppingId = flavorToppingRepository.findByToppingId(topping.getId());
            flavorToppings.addAll(flavorToppingsByToppingId);
        }

        for (FlavorTopping flavorTopping : flavorToppings) {
            Flavor flavor = flavorRepository.findById(flavorTopping.getFlavor().getId()).orElseThrow(NoSuchFlavorException::new);
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

        Long syrupTypeId = syrupTypeRepository.findIdByNameKR(syrupType).orElseThrow(NoSuchTypeException::new);
        List<Syrup> syrups = syrupRepository.findBySyrupTypeId(syrupTypeId);

        List<FlavorSyrup> flavorSyrups = new ArrayList<>();
        for (Syrup syrup : syrups) {
            List<FlavorSyrup> flavorSyrupsBySyrupId = flavorSyrupRepository.findBySyrupId(syrup.getId());
            flavorSyrups.addAll(flavorSyrupsBySyrupId);
        }

        for (FlavorSyrup flavorSyrup : flavorSyrups) {
            Flavor flavor = flavorRepository.findById(flavorSyrup.getFlavor().getId()).orElseThrow(NoSuchFlavorException::new);
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

        Long allergenId = allergenRepository.findIdByNameKR(allergen).orElseThrow(NoSuchTypeException::new);

        List<FlavorAllergen> flavorAllergens = flavorAllergenRepository.findByAllergenId(allergenId);

        for (FlavorAllergen flavorAllergen : flavorAllergens) {
            Flavor flavor = flavorRepository.findById(flavorAllergen.getFlavor().getId()).orElseThrow(NoSuchFlavorException::new);
            if (!flavors.contains(flavor)) {
                System.out.println(flavorAllergen.getAllergen().getNameKR() + " 알레르기 성분을 갖는 플레이버 " + flavor.getNameKR());
                flavors.add(flavor);
            }
        }
        return flavors;
    }

    private List<Flavor> orderFlavorsByOnSale(List<Flavor> flavors) {
        List<Flavor> orderedFilteredFlavorList = new ArrayList<>();
        if (!flavors.isEmpty()) {
            for (Flavor flavor : flavors) {
                if (flavor.getOnSale().isOnSale()) {
                    orderedFilteredFlavorList.add(flavor);
                }
            }
            for (Flavor flavor : flavors) {
                if (!flavor.getOnSale().isOnSale()) {
                    orderedFilteredFlavorList.add(flavor);
                }
            }
        }
        return orderedFilteredFlavorList;
    }

    private List<FlavorResponse> flavorsToFlavorResponses(List<Flavor> flavors) {
        List<FlavorResponse> result = new ArrayList<>();
        for (Flavor flavor : flavors) {
            result.add(flavorToFlavorResponse(flavor));
        }
        return result;
    }

    private FlavorResponse flavorToFlavorResponse(Flavor flavor) {
        return new FlavorResponse(flavor.getId(), flavor.getNameKR(), flavor.getNameEN(),
                flavor.getKcal(), flavor.isSignature(),
                InfoResponse.toInfoResponse(flavor.getInfo()),
                ImageResponse.toImageResponse(flavor.getImage()),
                OnSaleResponse.toOnSaleResponse(flavor.getOnSale()),
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

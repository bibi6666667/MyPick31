package bibi.demo.domain.type.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseTypeValue {

    VANILLA(1L, "바닐라", "vanilla"),
    CHOCOLATE(2L, "초콜릿", "Chocolate"),
    CHEESE(3L, "치즈", "Cheese"),
    MINT(4L, "민트", "Mint"),
    GREEN_TEA(5L, "녹차", "Green tea"),
    FRUIT(6L, "과일", "Fruit"),
    YOGURT(7L, "요거트", "Yogurt"),
    NUT(8L, " 견과류", "Nut"),
    COFFEE(9L, "커피", "Coffee"),
    CARAMEL(10L, "카라멜", "Caramel"),
    COTTON_CANDY(11L, "솜사탕", "Cotton candy"),
    MILK(12L, "우유", "Milk"),
    CONDENSED_MILK_OR_CREAM(13L, "연유/크림", "Condensed milk/Cream"),
    SNACK_FOODS(14L, "과자", "Snack foods"),
    GRAIN_OR_TUBER(15L, "곡류/서류", "Grain/Tuber"),
    BREAD_OR_RICE_CAKE(16L, "빵/떡", "Bread/Rice cake"),
    BEVERAGE(17L, "음료", "Beverage"),
    CANDY(18L, "사탕", "Candy");


    private Long id;
    private String nameKR;
    private String nameEN;

    public static Long getBaseTypeIdByName(String name) {
        BaseTypeValue value = BaseTypeValue.valueOf(name);
        System.out.println(value.nameKR);
        return value.getId();
    }
}

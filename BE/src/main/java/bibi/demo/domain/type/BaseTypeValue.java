package bibi.demo.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseTypeValue {

    VANILLA(1L, "바닐라", "vanilla");

    private Long id;
    private String nameKR;
    private String nameEN;

    public static Long getBaseTypeIdByNameEN(String nameEN) {
        System.out.println(nameEN);
        BaseTypeValue value = BaseTypeValue.valueOf(nameEN);
        return value.getId();
    }
}

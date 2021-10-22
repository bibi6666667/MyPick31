package bibi.demo.response;

import lombok.Getter;

import java.util.List;

@Getter
public class FlavorResponse {

    private Long id;
    private String nameKR;
    private String nameEN;
    private int kcal;
    private boolean isSignature;
    private InfoResponse info;
    private ImageResponse image;
    private OnSaleResponse onSale;
    private List<BaseResponse> baseList;
    private List<ToppingResponse> toppingList;
    private List<SyrupResponse> syrupList;
    private List<AllergenResponse> allergenList;
    private Long pickNum;

    public FlavorResponse(Long id, String nameKR, String nameEN, int kcal, boolean isSignature,
                          InfoResponse info, ImageResponse image, OnSaleResponse onSale,
                          List<BaseResponse> baseList, List<ToppingResponse> toppingList,
                          List<SyrupResponse> syrupList, List<AllergenResponse> allergenList, Long pickNum) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
        this.kcal = kcal;
        this.isSignature = isSignature;
        this.info = info;
        this.image = image;
        this.onSale = onSale;
        this.baseList = baseList;
        this.toppingList = toppingList;
        this.syrupList = syrupList;
        this.allergenList = allergenList;
        this.pickNum = pickNum;
    }
}

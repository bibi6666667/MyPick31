package bibi.demo.response;

import bibi.demo.domain.Image;
import bibi.demo.domain.Info;
import bibi.demo.domain.OnSale;
import bibi.demo.domain.flavor.*;
import lombok.Getter;

import java.util.List;

@Getter
public class FlavorResponse {

    private Long id;
    private String nameKR;
    private String nameEN;
    private int kcal;
    private boolean isSignature;
    private Info info;
    private Image image;
    private OnSale onSale;
    private List<BaseResponse> baseList;
    private List<ToppingResponse> toppingList;
    private List<SyrupResponse> syrupList;
    private List<AllergenResponse> allergenList;

    public FlavorResponse(Long id, String nameKR, String nameEN, int kcal, boolean isSignature,
                          Info info, Image image, OnSale onSale,
                          List<BaseResponse> baseList, List<ToppingResponse> toppingList, List<SyrupResponse> syrupList, List<AllergenResponse> allergenList) {
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
    }
}

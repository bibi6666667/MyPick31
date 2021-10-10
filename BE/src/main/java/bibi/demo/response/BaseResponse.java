package bibi.demo.response;

import bibi.demo.domain.Base;
import lombok.Getter;

@Getter
public class BaseResponse {

    private Long id;
    private String nameKR;
    private String nameEN;
    private boolean isSherbet;
    private boolean isSorbet;
    private Long baseTypeId;

    public BaseResponse(Long id, String nameKR, String nameEN,
                        boolean isSherbet, boolean isSorbet, Long baseTypeId) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
        this.isSherbet = isSherbet;
        this.isSorbet = isSorbet;
        this.baseTypeId = baseTypeId;
    }

    public static BaseResponse toBaseResponse(Base base) {
        return new BaseResponse(base.getId(), base.getNameKR(), base.getNameEN(),
                base.isSherbet(), base.isSorbet(), base.getBaseTypeId());
    }
}

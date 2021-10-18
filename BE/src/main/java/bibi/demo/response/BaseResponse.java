package bibi.demo.response;

import bibi.demo.domain.Base;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class BaseResponse {

    @JsonIgnore
    private Long id;

    private String nameKR;
    private String nameEN;

    @JsonIgnore
    private boolean isSherbet;
    @JsonIgnore
    private boolean isSorbet;
    @JsonIgnore
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

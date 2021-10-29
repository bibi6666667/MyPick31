package bibi.demo.response.flavor;

import bibi.demo.domain.Syrup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class SyrupResponse {

    @JsonIgnore
    private Long id;

    private String nameKR;
    private String nameEN;

    @JsonIgnore
    private Long syrupTypeId;

    public SyrupResponse(Long id, String nameKR, String nameEN, Long syrupTypeId) {
        this.id = id;
        this.nameKR = nameKR;
        this.nameEN = nameEN;
        this.syrupTypeId = syrupTypeId;
    }

    public static SyrupResponse toSyrupResponse(Syrup syrup) {
        return new SyrupResponse(syrup.getId(), syrup.getNameKR(), syrup.getNameEN(), syrup.getSyrupTypeId());
    }
}

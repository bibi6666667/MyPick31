package bibi.demo.response;

import bibi.demo.domain.Syrup;
import lombok.Getter;

@Getter
public class SyrupResponse {
    private Long id;
    private String nameKR;
    private String nameEN;
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

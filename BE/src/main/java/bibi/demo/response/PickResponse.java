package bibi.demo.response;

import bibi.demo.domain.Pick;
import lombok.Getter;

@Getter
public class PickResponse {

    private Long id;
    private Long flavorId;
    private Long userId;

    public PickResponse(Long id, Long flavorId, Long userId) {
        this.id = id;
        this.flavorId = flavorId;
        this.userId = userId;
    }

    public static PickResponse toPickResponse(Pick pick) {
        return new PickResponse(pick.getId(), pick.getFlavorId(), pick.getUserId());
    }
}

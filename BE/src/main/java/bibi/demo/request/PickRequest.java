package bibi.demo.request;

import lombok.Getter;

@Getter
public class PickRequest {

    private Long flavorId;
    private Long userId;

    public PickRequest() {
    }

    public PickRequest(Long flavorId, Long userId) {
        this.flavorId = flavorId;
        this.userId = userId;
    }
}

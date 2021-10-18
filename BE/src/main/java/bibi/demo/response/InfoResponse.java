package bibi.demo.response;

import bibi.demo.domain.Info;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class InfoResponse {

    @JsonIgnore
    private Long id;

    private String content;

    public InfoResponse(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public static InfoResponse toInfoResponse(Info info) {
        return new InfoResponse(info.getId(), info.getContent());
    }
}

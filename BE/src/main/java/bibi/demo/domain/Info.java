package bibi.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("has_info")
    private boolean hasInfo;

    private String content;

    public Info() {
    }

    public Info(Long id, boolean hasInfo) {
        this.id = id;
        this.hasInfo = hasInfo;
    }

    public Info(Long id, boolean hasInfo, String content) {
        this(id, hasInfo);
        this.content = content;
    }
}


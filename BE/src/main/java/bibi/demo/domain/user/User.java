package bibi.demo.domain.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Column(name = "access_token")
    private String accessToken;

    public User() {
    }

    public User(String name, String email, String accessToken) {
        this.name = name;
        this.email = email;
        this.accessToken = accessToken;
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(Long id, String name, String email, String accessToken) {
        this(id, name, email);
        this.accessToken = accessToken;
    }
}

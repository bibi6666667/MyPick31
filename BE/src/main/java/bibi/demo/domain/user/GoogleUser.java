package bibi.demo.domain.user;

import lombok.Getter;

@Getter
public class GoogleUser {
    // public -> private
    private String id;
    private String email;
    private Boolean verifiedEmail;
    private String name;
    private String givenName;
    private String familyName;
    private String picture;
    private String locale;

    public GoogleUser() {
    }

    public GoogleUser(String id, String email, Boolean verifiedEmail,
                      String name, String givenName, String familyName,
                      String picture, String locale) {
        this.id = id;
        this.email = email;
        this.verifiedEmail = verifiedEmail;
        this.name = name;
        this.givenName = givenName;
        this.familyName = familyName;
        this.picture = picture;
        this.locale = locale;
    }

    public User toUser(String accessToken) {
        return new User(name, email, accessToken);
    }
}

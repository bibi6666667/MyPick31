package bibi.demo.login;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class GoogleOAuthComponent {

    public static final String BEARER = "bearer";
    public static final String GOOGLE_RESPONSE_TYPE = "code";
    public static final String GRANT_TYPE = "authorization_code";

    @Value("${google.client.id}")
    public String GOOGLE_CLIENT_ID;

    @Value("${google.client.secret}")
    public String GOOGLE_CLIENT_SECRET;

    @Value("${google.endpoint}")
    public String GOOGLE_ENDPOINT;

    @Value("${google.redirect.uri}")
    public String GOOGLE_REDIRECT_URI;

    @Value("${google.scope}")
    public String GOOGLE_SCOPE;

}

package bibi.demo.login;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class GoogleOAuthComponent {

    @Value("${google.client.id}")
    public String GOOGLE_CLIENT_ID;

    @Value("${google.client.secret}")
    public String GOOGLE_CLIENT_SECRET;
}

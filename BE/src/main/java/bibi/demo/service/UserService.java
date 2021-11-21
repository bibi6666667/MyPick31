package bibi.demo.service;

import bibi.demo.domain.user.GoogleUser;
import bibi.demo.domain.user.User;
import bibi.demo.exception.UserNotFoundException;
import bibi.demo.login.JwtTokenProvider;
import bibi.demo.login.OAuthToken;
import bibi.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final GoogleOAuthService googleOAuthService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, GoogleOAuthService googleOAuthService, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.googleOAuthService = googleOAuthService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String googleOauthLogin(String code) {
        ResponseEntity<String> accessTokenResponse = googleOAuthService.createPostRequest(code);
        OAuthToken oAuthToken = googleOAuthService.getAccessToken(accessTokenResponse);
        logger.info("Access Token: {}", oAuthToken.getAccessToken());

        ResponseEntity<String> userInfoResponse = googleOAuthService.createGetRequest(oAuthToken);
        GoogleUser googleUser = googleOAuthService.getUserInfo(userInfoResponse);
        logger.info("Google User Name: {}", googleUser.getName());

        if (!isJoinedUser(googleUser)) {
            signUp(googleUser, oAuthToken);
        }
        User user = userRepository.findByEmail(googleUser.getEmail()).orElseThrow(UserNotFoundException::new);
        return jwtTokenProvider.createToken(user.getId());
    }

    private boolean isJoinedUser(GoogleUser googleUser) {
        Optional<User> users = userRepository.findByEmail(googleUser.getEmail());
        return users.isPresent();
    }

    private void signUp(GoogleUser googleUser, OAuthToken oAuthToken) {
        User user = googleUser.toUser(oAuthToken.getAccessToken());
        userRepository.save(user);
    }

}

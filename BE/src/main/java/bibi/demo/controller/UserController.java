package bibi.demo.controller;

import bibi.demo.login.GoogleOAuthComponent;
import bibi.demo.response.ApiResponse;
import bibi.demo.response.StatusEnum;
import bibi.demo.response.TokenResponse;
import bibi.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // RestController가 아닌 Controller 여야 redirect가 동작함
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private GoogleOAuthComponent googleOAuthComponent;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login/google")
    public String googleLogin() {
        return "redirect:" + googleOAuthComponent.GOOGLE_ENDPOINT
                + "?client_id=" + googleOAuthComponent.GOOGLE_CLIENT_ID
                + "&redirect_uri=" + googleOAuthComponent.GOOGLE_REDIRECT_URI
                + "&response_type=" + googleOAuthComponent.GOOGLE_RESPONSE_TYPE
                + "&scope=" + googleOAuthComponent.GOOGLE_SCOPE;
    }

    @GetMapping("/oauth/google/callback") // redirection URI
    public ResponseEntity<ApiResponse> googleOAuthLogin(String code) {
        String token = userService.googleOauthLogin(code);
        ApiResponse apiResponse = new ApiResponse(StatusEnum.OK, new TokenResponse(token, googleOAuthComponent.BEARER));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

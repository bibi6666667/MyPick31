package bibi.demo.login;

import bibi.demo.exception.TokenEmptyException;
import bibi.demo.exception.TokenExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 인터셉터 구현체. AppConfig 에 등록됨
@Component
public class BearerAuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(BearerAuthInterceptor.class);

    private AuthorizationExtractor authorizationExtractor;
    private JwtTokenProvider jwtTokenProvider;

    public BearerAuthInterceptor(AuthorizationExtractor authorizationExtractor,
                                 JwtTokenProvider jwtTokenProvider) {
        this.authorizationExtractor = authorizationExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info(">>> interceptor.preHandle 호출;");
        String token = authorizationExtractor.extract(request, "Bearer");

        if (token.isEmpty()) {
            throw new TokenEmptyException();
        }
        if (!jwtTokenProvider.validateToken(token)) {
            throw new TokenExpiredException();
        }

        Long id = jwtTokenProvider.getSubject(token);
        request.setAttribute("id", id);
        return true;
    }
}

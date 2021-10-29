package bibi.demo.config;

import bibi.demo.login.BearerAuthInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
// JWT 토큰 검증을 위한 인터셉터를 등록하는 클래스.
public class AppConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    private final BearerAuthInterceptor bearerAuthInterceptor;

    public AppConfig(BearerAuthInterceptor bearerAuthInterceptor) { // 인터셉터 구현체 생성, 등록
        this.bearerAuthInterceptor = bearerAuthInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info(">>> 인터셉터 등록");
        registry.addInterceptor(bearerAuthInterceptor)
                .addPathPatterns("/pick/**");
                //.addPathPatterns("/flavor/pick/{flavorId}") 와 같이 등록된 url 에서만 토큰을 검증하게 됨
    }
}

package bibi.demo.exception;

public class TokenExpiredException extends RuntimeException {

    private static final String TOKEN_EXPIRED = "유효기간이 지난 토큰입니다.";

    public TokenExpiredException() {
        super(TOKEN_EXPIRED);
    }
}

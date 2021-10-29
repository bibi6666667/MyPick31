package bibi.demo.exception;

public class TokenEmptyException extends RuntimeException {

    private static final String TOKEN_EMPTY = "토큰이 없습니다.";

    public TokenEmptyException() {
        super(TOKEN_EMPTY);
    }
}

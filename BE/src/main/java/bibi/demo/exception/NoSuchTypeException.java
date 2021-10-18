package bibi.demo.exception;

public class NoSuchTypeException extends RuntimeException {
    private static final String NO_SUCH_TYPE = "입력한 베이스/토핑/시럽 타입 또는 알레르기성분이 존재하지 않습니다.";

    public NoSuchTypeException() {
        super(NO_SUCH_TYPE);
    }
}

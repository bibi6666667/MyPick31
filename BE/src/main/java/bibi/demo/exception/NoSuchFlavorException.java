package bibi.demo.exception;

public class NoSuchFlavorException extends RuntimeException {
    private static final String NO_SUCH_FLAVOR = "해당 플레이버가 존재하지 않습니다";

    public NoSuchFlavorException() {
        super(NO_SUCH_FLAVOR);
    }
}

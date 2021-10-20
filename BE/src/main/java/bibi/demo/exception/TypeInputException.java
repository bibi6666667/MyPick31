package bibi.demo.exception;

public class TypeInputException extends RuntimeException {
    private static final String TYPE_INPUT = "타입1 없이 타입2만 지정할 수 없습니다.";

    public TypeInputException() {
        super(TYPE_INPUT);
    }
}

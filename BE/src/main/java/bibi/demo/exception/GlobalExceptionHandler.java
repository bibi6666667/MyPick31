package bibi.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TypeInputException.class)
    public String handleTypeInputException(TypeInputException e) {
        return e.getMessage();
    }

    @ExceptionHandler(NoSuchTypeException.class)
    public String handleNoSuchTypeException(NoSuchTypeException e) {
        return e.getMessage();
    }

    @ExceptionHandler(NoSuchFlavorException.class)
    public String handleNoSuchFlavorException(NoSuchTypeException e) {
        return e.getMessage();
    }
}

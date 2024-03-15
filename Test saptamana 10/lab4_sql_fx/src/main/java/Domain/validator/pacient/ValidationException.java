package Domain.validator.pacient;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
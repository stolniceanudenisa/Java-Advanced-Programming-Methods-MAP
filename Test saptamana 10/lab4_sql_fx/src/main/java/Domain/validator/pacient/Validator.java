package Domain.validator.pacient;

public interface Validator<T> {
    boolean validate(T entity) throws ValidationException;
}
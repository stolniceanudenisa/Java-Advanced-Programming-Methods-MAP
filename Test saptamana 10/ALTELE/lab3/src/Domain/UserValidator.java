package Domain;

public class UserValidator implements Validator<User> {
    @Override
    public void validate(User entity) throws ValidatorException {
        if(entity.getId_entity() <= 0) {
            throw new ValidatorException("Id-ul trebuie sa fie strict pozitiv!");
        }
        if(entity.getAge() > 110 || entity.getAge() <= 0) {
            throw new ValidatorException("Varsta nu este valida!");
        }
    }
}

package domain.validators;

import domain.*;

public class UserValidator implements Validator<User>{
    @Override
    public void validate(User entity) throws ValidatorException {
        if(entity.getId()<= 0){
            throw new ValidatorException("Id-ul nu poate fi mai mic sau egal cu 0!");
        }
        if(entity.getAge() > 100){
            throw new ValidatorException("Varsta nu e valida!");
        }
    }
}

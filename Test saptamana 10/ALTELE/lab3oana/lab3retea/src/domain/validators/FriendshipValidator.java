package domain.validators;

import domain.*;

public class FriendshipValidator implements Validator<Friendship> {
    @Override
    public void validate(Friendship entity) throws ValidatorException {
        if(entity.getId()<= 0){
            throw new ValidatorException("Id-ul nu poate fi mai mic sau egal cu 0!");
        }
    }
}

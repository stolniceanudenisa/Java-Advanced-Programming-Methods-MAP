package Domain;

public class FriendshipValidator implements Validator<Friendship>{
    @Override
    public void validate(Friendship entity) throws ValidatorException {
        if(entity.getId_entity() <= 0) {
            throw new ValidatorException("Id-ul trebuie sa fie strict pozitiv!");
        }
    }
}

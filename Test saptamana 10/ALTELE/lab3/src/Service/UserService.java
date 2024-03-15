package Service;

import Domain.Friendship;
import Domain.User;
import Domain.UserValidator;
import Repo.RepoFriendship;
import Repo.RepoUser;

import java.util.List;

public class UserService {
    RepoUser repoUser;
    UserValidator userValidator;
    RepoFriendship repoFriendship;

    public UserService(RepoUser repoUser, UserValidator userValidator, RepoFriendship repoFriendship) {
        this.repoUser = repoUser;
        this.userValidator = userValidator;
        this.repoFriendship = repoFriendship;
    }

    public void add(User user) {
        if(repoUser.find_one(user.getId_entity()) != null)
            throw new RuntimeException("Id-ul exista deja in lista!");
        userValidator.validate(user);
        repoUser.add(user);
    }

    public void delete(int id_to_delete) {
        if(repoUser.find_one(id_to_delete) == null)
            throw new RuntimeException("Id-ul nu exista in lista!");
        repoUser.delete(id_to_delete);
        for(Friendship friendship: repoFriendship.getFriendships()) {
            if(id_to_delete == friendship.getUser1().getId_entity() || id_to_delete == friendship.getUser2().getId_entity())
                repoFriendship.delete(friendship.getId_entity());
        }
    }

    public List<User> get_all_users() {
        return repoUser.getUsers();
    }

    public User get_by_id(int id_user) {
        return repoUser.find_one(id_user);
    }

}

package Service;

import Domain.Friendship;
import Domain.FriendshipValidator;
import Domain.User;
import Repo.RepoFriendship;

import java.util.List;

public class FriendshipService {
    RepoFriendship repoFriendship;
    FriendshipValidator friendshipValidator;
    UserService userService;

    public FriendshipService(RepoFriendship repoFriendship, FriendshipValidator friendshipValidator, UserService userService) {
        this.repoFriendship = repoFriendship;
        this.friendshipValidator = friendshipValidator;
        this.userService = userService;
    }

    public void add(Friendship friendship) {
        User user1 = friendship.getUser1();
        User user2 = friendship.getUser2();

        if (repoFriendship.find_one(friendship.getId_entity()) != null)
            throw new RuntimeException("Id-ul dat exista deja!");

        if (userService.get_by_id(user1.getId_entity()) == null)
            throw new RuntimeException("User1 nu exista!");

        if (userService.get_by_id(user2.getId_entity()) == null)
            throw new RuntimeException("User2 nu exista!");

        if(userService.get_by_id(user1.getId_entity()) == userService.get_by_id(user2.getId_entity()))
            throw new RuntimeException("User1 si User2 trebuie sa fie diferiti!");

        for(Friendship friendship1: repoFriendship.getFriendships()) {
            if(friendship1.getUser1() == friendship.getUser1() && friendship1.getUser2() == friendship.getUser2())
                throw new RuntimeException("Exista deja o prietenie intre User1 si User2 introdusi!");
            if(friendship1.getUser1() == friendship.getUser2() && friendship1.getUser2() == friendship.getUser1())
                throw new RuntimeException("Exista deja o prietenie intre User1 si User2 introdusi!");
        }

        friendshipValidator.validate(friendship);
        repoFriendship.add(friendship);
    }

    public void delete(int id_to_delete) {
        if(repoFriendship.find_one(id_to_delete) == null)
            throw new RuntimeException("Id-ul dat nu exista in lista!");
        repoFriendship.delete(id_to_delete);
    }

    public List<Friendship> get_all_friendship() {
        return repoFriendship.getFriendships();
    }

}

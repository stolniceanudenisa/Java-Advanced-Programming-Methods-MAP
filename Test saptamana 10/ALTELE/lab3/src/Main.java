import Domain.FriendshipValidator;
import Domain.UserValidator;
import Repo.RepoFriendship;
import Repo.RepoUser;
import Service.FriendshipService;
import Service.UserService;
import UI.Console;

public class Main {
    public static void main(String[] args) {

        UserValidator userValidator = new UserValidator();
        FriendshipValidator friendshipValidator = new FriendshipValidator();

        RepoUser repoUser = new RepoUser();
        RepoFriendship repoFriendship = new RepoFriendship();

        UserService userService = new UserService(repoUser, userValidator, repoFriendship);
        FriendshipService friendshipService = new FriendshipService(repoFriendship, friendshipValidator, userService);

        Console console = new Console(userService, friendshipService);

        console.run();

    }
}
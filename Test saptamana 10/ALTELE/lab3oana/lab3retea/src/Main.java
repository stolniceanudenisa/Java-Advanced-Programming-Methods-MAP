import controller.*;
import domain.*;
import domain.validators.*;
import repository.*;
import ui.*;

public class Main {
    public static void main(String[] args) {
        /**
         * @params a,b
         */
        UserValidator userValidator = new UserValidator();
        FriendshipValidator friendshipValidator = new FriendshipValidator();
        RepoUser repoUser = new RepoUser();
        RepoFriendship repoFriendship = new RepoFriendship();
        ControllerUser controllerUser = new ControllerUser(repoUser,userValidator,repoFriendship);
        ControllerFriendship controllerFriendship = new ControllerFriendship(repoFriendship,friendshipValidator,controllerUser);
        Console console = new Console(controllerUser, controllerFriendship);
        console.run();
    }
}
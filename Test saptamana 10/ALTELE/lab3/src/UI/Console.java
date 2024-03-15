package UI;

import Domain.Friendship;
import Domain.User;
import Service.FriendshipService;
import Service.UserService;

import java.util.Scanner;

public class Console {
    UserService userService;
    FriendshipService friendshipService;
    public static boolean gameOver = false;

    public Console(UserService userService, FriendshipService friendshipService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
    }

    public void run() {
        while (!gameOver) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("1. Add user");
            System.out.println("2. Delete user");
            System.out.println("3. Show all users");
            System.out.println("4. Add friendship");
            System.out.println("5. Delete friendship");
            System.out.println("6. Show all friendships");
            System.out.println("7. Exit");

            System.out.println("Enter option: ");
            int option = keyboard.nextInt();
            if(option >= 1 && option<= 6) {
                if(option == 1) {
                    add_user();
                }
                if(option == 2) {
                    delete_user();
                }
                if(option == 3) {
                    show_all_users();
                }
                if(option == 4) {
                    add_friendship();
                }
                if(option == 5) {
                    delete_friendship();
                }
                if(option == 6) {
                    show_all_friendships();
                }
            } else {
                gameOver = true;
            }
        }
    }




    private void add_user() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter id: ");
            int id_user = keyboard.nextInt();

            System.out.println("Enter first name: ");
            String first_name = keyboard.next();

            System.out.println("Enter last name: ");
            String last_name = keyboard.next();

            System.out.println("Enter username: ");
            String username = keyboard.next();

            System.out.println("Enter age: ");
            int age = keyboard.nextInt();

            System.out.println("Enter password: ");
            String password = keyboard.next();

            User user = new User(id_user, first_name, last_name, username, age, password);
            userService.add(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void delete_user() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the id of the user you want to delete: ");
            int id_to_delete = keyboard.nextInt();
            userService.delete(id_to_delete);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void show_all_users() {
        boolean ok = false;
        for(User user: userService.get_all_users()) {
            System.out.println(user);
            ok = true;
        }
        if (!ok) {
            System.out.println("Dictionarul este gol!");
        }
    }

    private void add_friendship() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter friendship id: ");
            int id_friendship = keyboard.nextInt();

            System.out.println("Enter id of User1: ");
            int id_user1 = keyboard.nextInt();
            User user1 = userService.get_by_id(id_user1);

            System.out.println("Enter id of User2: ");
            int id_user2 = keyboard.nextInt();
            User user2 = userService.get_by_id(id_user2);

            Friendship friendship = new Friendship(id_friendship, user1, user2);
            friendshipService.add(friendship);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void delete_friendship() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the id of the friendship you want to delete: ");
            int id_to_delete = keyboard.nextInt();
            friendshipService.delete(id_to_delete);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void show_all_friendships() {
        boolean ok = false;
        for(Friendship friendship: friendshipService.get_all_friendship()) {
            System.out.println(friendship);
            ok = true;
        }
        if (!ok) {
            System.out.println("Dictionarul este gol!");
        }
    }

}

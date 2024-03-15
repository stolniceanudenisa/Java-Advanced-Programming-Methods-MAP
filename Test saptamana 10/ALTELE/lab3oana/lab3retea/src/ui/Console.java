package ui;

import controller.*;
import domain.*;

import java.util.*;

public class Console {
    ControllerUser controllerUser;
    ControllerFriendship controllerFriendship;
    public static boolean gameOver = false;
    //Scanner keyboard = new Scanner(System.in);

    public Console(ControllerUser controllerUser, ControllerFriendship controllerFriendship) {
        this.controllerUser = controllerUser;
        this.controllerFriendship = controllerFriendship;
    }

    private void add_user() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter id: ");
            long id = keyboard.nextLong();

            System.out.println("Enter first name: ");
            String firstName = keyboard.next();

            System.out.println("Enter last name: ");
            String lastName = keyboard.next();

            System.out.println("Enter mail: ");
            String mail = keyboard.next();

            System.out.println("Enter age: ");
            int age = keyboard.nextInt();

            System.out.println("Enter password: ");
            String password = keyboard.next();


            User user = new User(id, firstName, lastName, mail, age, password);
            controllerUser.add(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    private void showAllUsers() {
        for (User u : controllerUser.getAll()) {
            System.out.println(u);
        }
    }

    private void deleteUser() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the id of the user you want to delete: ");
            long id = keyboard.nextLong();
            controllerUser.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void add_friendship() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the id of the friendship: ");
            long id = keyboard.nextLong();

            System.out.println("Enter the id of the first user: ");
            long id1 = keyboard.nextLong();
            User user = controllerUser.getById(id1);

            System.out.println("Enter the id of the second user: ");
            long id2 = keyboard.nextLong();
            User user2 = controllerUser.getById(id2);

            Friendship friendship = new Friendship(id, user, user2);

            controllerFriendship.add(friendship);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAllFriendship() {
        for (Friendship f : controllerFriendship.getAll()) {
            System.out.println(f);
        }
    }

    private void deleteFriendship() {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the id of the friendship you want to delete: ");
            long id = keyboard.nextLong();
            controllerFriendship.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void run() {
        while (!gameOver) {
            Scanner keyboard6 = new Scanner(System.in);
            System.out.println("1. Add user ");
            System.out.println("2. Show all users ");
            System.out.println("3. Delete user ");
            System.out.println("4. Add friendship ");
            System.out.println("5. Show all friendships ");
            System.out.println("6. Delete friendship ");
            System.out.println("7. Exit ");

            System.out.println("Enter option: ");
            int option = keyboard6.nextInt();
            if (option >= 1 && option <= 6) {
                if (option == 1) {
                    add_user();
                }
                if (option == 2) {
                    showAllUsers();
                }
                if (option == 3) {
                    deleteUser();
                }
                if (option == 4) {
                    add_friendship();
                }
                if (option == 5) {
                    showAllFriendship();
                }
                if (option == 6) {
                    deleteFriendship();
                }
            } else {
                gameOver = true;
            }
        }
    }
}

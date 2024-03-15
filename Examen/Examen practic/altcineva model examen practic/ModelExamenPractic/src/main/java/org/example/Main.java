package org.example;

import org.example.Repository.RepoDbStudent;
import org.example.Service.ServiceStudent;
import org.example.UserInterface.Console;

public class Main {
    public static void main(String[] args) {


        String username="postgres";
        String pasword="220602";
        String url="jdbc:postgresql://localhost:5432/studenti";

        RepoDbStudent repo = new RepoDbStudent(url, username, pasword);

        ServiceStudent service = new ServiceStudent(repo);

        Console console = new Console(service);
        console.runMenu();
    }
}
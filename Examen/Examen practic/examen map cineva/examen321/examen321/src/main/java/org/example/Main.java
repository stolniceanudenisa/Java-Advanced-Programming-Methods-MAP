package org.example;

import org.example.domain.validator.AlbumValidator;
import org.example.repository.AlbumRepository;
import org.example.service.Service;
import org.example.ui.Console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/examen";
        String username = "postgres";
        String password = "armadilo12";

        Connection connection = DriverManager.getConnection(url, username, password);

        AlbumRepository albumRepository = new AlbumRepository(connection, new AlbumValidator());
        Service service = new Service(albumRepository);
        Console console = new Console(service);
        console.run();
    }
}
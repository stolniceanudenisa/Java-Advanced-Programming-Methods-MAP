package com.example.eu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication2 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication2.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


//        String numeFisier = "hello-view.fxml";
//
//        try {
//            // Obține calea absolută către directorul curent
//            String caleDirectorCurent = System.getProperty("user.dir");
//
//            // Creează un obiect File pentru noul fișier în directorul curent
//            File fisier = new File(caleDirectorCurent, numeFisier);
//
//            // Creează fișierul dacă nu există
//            if (fisier.createNewFile()) {
//                System.out.println("Fișierul a fost creat la: " + fisier.getAbsolutePath());
//
//                // Scrie ceva în fișier (în cazul în care este necesar)
//                FileWriter writer = new FileWriter(fisier);
//                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                        "<VBox xmlns:fx=\"http://javafx.com/fxml\" alignment=\"CENTER\" spacing=\"20.0\" fx:controller=\"com.example.eu.HelloController\">\n" +
//                        "    <padding>\n" +
//                        "        <!--... Continuarea conținutului XML ...-->\n" +
//                        "    </padding>\n" +
//                        "    <!--... Continuarea conținutului XML ...-->\n" +
//                        "</VBox>");
//                writer.close();
//            } else {
//                System.out.println("Fișierul există deja la: " + fisier.getAbsolutePath());
//            }
//        } catch (IOException e) {
//            System.out.println("A apărut o eroare la crearea fișierului: " + e.getMessage());
//        }




    }

    public static void main(String[] args) {
        launch();
    }
}
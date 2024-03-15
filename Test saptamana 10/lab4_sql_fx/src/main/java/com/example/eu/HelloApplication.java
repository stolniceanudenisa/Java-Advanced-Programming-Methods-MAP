package com.example.eu;//package com.example.eu.controllers;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class HelloApplication extends Application {
//
//
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        initView(stage);
//        stage.show();
//    }
//
//
//    private void initView(Stage primaryStage) throws IOException {
//        FXMLLoader stageLoader = new FXMLLoader();
//        stageLoader.setLocation(getClass().getResource("hello-view.fxml"));
//        AnchorPane LogInLayout = stageLoader.load();
//        primaryStage.setScene(new Scene(LogInLayout));
//        primaryStage.setTitle("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
//
//        //Image icon = new Image("/com.example.reteadesocializare/imgs/Soboclan.jpg");
//       // primaryStage.getIcons().add(icon);
//
//        PacientsController pacientsController = stageLoader.getController();
//
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}
//
//
//
//
//
//
//
//
////    public void start(Stage stage) throws IOException {
////        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("aa.fxml"));
////        Scene scene = new Scene(fxmlLoader.load(), 600, 400); // Set the desired dimensions
////       // stage.setTitle("Lista Pacienților");
////        stage.setScene(scene);
////        stage.show();
////    }
//
////    public void start(Stage stage) throws IOException {
////        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("aa.fxml"));
////        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
////
////        // Get the controller associated with the FXML
////        PacientsViewController controller = fxmlLoader.getController();
////
////        // Create an instance of PacientService
////        PacientService pacientService = new PacientService(new PatientsDbRepository(), new PacientValidator());
////
////        // Fetch data from PacientService and set it to TableView in PacientsViewController
////        controller.initializeTableData(pacientService.getAll2());
////
////        stage.setScene(scene);
////        stage.show();
////    }
//
////
////    public void showPacientsView() throws IOException {
////        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pacients-view.fxml"));
////        Scene scene = new Scene(fxmlLoader.load(), 600, 400); // Setează dimensiunile dorite
////        Stage pacientsStage = new Stage();
////        pacientsStage.setTitle("Lista Pacienților");
////        pacientsStage.setScene(scene);
////        pacientsStage.show();
////    }
//
////    public void showPacientsView() throws IOException {
////        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pacients-view.fxml"));
////        PacientService pacientService = new PacientService(new PatientsDbRepository(), new PacientValidator());
////        fxmlLoader.setController(new PacientsViewController(pacientService));
////        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
////        Stage pacientsStage = new Stage();
////        pacientsStage.setTitle("Lista Pacienților");
////        pacientsStage.setScene(scene);
////        pacientsStage.show();
////    }
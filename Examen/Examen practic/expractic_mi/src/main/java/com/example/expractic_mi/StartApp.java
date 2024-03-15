package com.example.expractic_mi;


import com.example.expractic_mi.controller.NotaController;
import com.example.expractic_mi.service.ServiceManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class StartApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(StartApp.class.getResource("notaView.fxml"));
        //FXMLLoader loader = new FXMLLoader(TestSem8.class.getResource("notaView.fxml"));
//
        AnchorPane root=loader.load();

        NotaController ctrl=loader.getController();
        ctrl.setService(new ServiceManager());

        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setTitle("Hello World");
        primaryStage.show();

//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        primaryStage.setTitle("Hello!");
//        primaryStage.setScene(scene);
//        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}

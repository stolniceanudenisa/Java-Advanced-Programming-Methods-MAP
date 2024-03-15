package com.example.eu;//package com.example.eu.controllers;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.layout.AnchorPane;
//
//public class HelloController {
//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    private AnchorPane rootPane;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//        PacientsController.showPacientsPane(rootPane);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    private void openHowAreYouDoing() {
////        try {
////            FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("how-are-you-doing.fxml"));
////            stage.setScene(new Scene(fxmlLoader.load(), 600, 400));
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////private void openHowAreYouDoing() {
////    try {
////        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("how-are-you-doing.fxml"));
////        Parent root = fxmlLoader.load();
////        Stage secondaryStage = new Stage();
////        secondaryStage.setScene(new Scene(root, 600, 400));
////
////        HowAreYouDoingController controller = fxmlLoader.getController();
////        controller.setStage(secondaryStage);
////
////        secondaryStage.show();
////    } catch (IOException e) {
////        e.printStackTrace();
////    }
////}
//
//}
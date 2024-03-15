package com.example.eu;//package com.example.eu.controllers;
//
//import com.example.eu.Domain.Pacient;
//import com.example.eu.Service.PacientService;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.ListView;
//import javafx.scene.layout.AnchorPane;
//
//import java.io.IOException;
//
//public class PacientsController {
//    @FXML
//    private ListView<Pacient> pacientListView;
//
//    private static PacientService pacientService;
//
//    public static void setPacientService(PacientService service) {
//        pacientService = service;
//    }
//
//    public static void showPacientsPane(AnchorPane rootPane) {
//        try {
//            FXMLLoader loader = new FXMLLoader(PacientsController.class.getResource("pacients.fxml"));
//            AnchorPane pane = loader.load();
//
//            rootPane.getChildren().setAll(pane);
//
//            PacientsController controller = loader.getController();
//            controller.initialize();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void initialize() {
//        // Initialize pacientListView with data
//        if (pacientService != null) {
//            pacientListView.getItems().addAll(pacientService.getAll2());
//        }
//    }
//
//    @FXML
//    private void handleAddPacient() {
//        // Handle the addition of a new patient here
//        // Access PacientService to add a new patient
//    }
//
//    @FXML
//    private void handleDeletePacient() {
//        // Handle the deletion of a patient here
//        // Access PacientService to delete a patient
//    }
//
//
//}
package com.example.eu;//package com.example.eu;
//
//import Domain.Pacient;
//import Service.PacientService;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//
//import java.io.File;
//import java.net.URL;
//import java.util.Collection;
//
//public class PacientsViewController {
//    @FXML
//    private TableView<Pacient> pacientsTable;
//
//    @FXML
//    private TableColumn<Pacient, Integer> idColumn;
//
//    @FXML
//    private TableColumn<Pacient, String>  numeColumn;
//
//    @FXML
//    private TableColumn<Pacient, String> prenumeColumn;
//
//    @FXML
//    private TableColumn<Pacient, Integer> varstaColumn;
//
//    private final PacientService pacientService;
//
//    @FXML
//    private Label welcomeText;
//
//    public PacientsViewController(PacientService pacientService) {
//        this.pacientService = pacientService;
//    }
//
//    public void initializeTableData(Collection<Pacient> patients) {
//        // Assuming pacientsTable is a TableView instance declared with @FXML
//        pacientsTable.getItems().addAll(patients);
//    }
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//        //openPacientsView();
//    }
//
//
////    public void initialize() {
////        initializeTable();
////        displayPatients();
////    }
////    private void initializeTable() {
////        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
////        numeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNume()));
////        prenumeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenume()));
////        varstaColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVarsta()).asObject());
////    }
////
////    private void displayPatients() {
////        Collection<Pacient> patients = pacientService.getAll2();
////        pacientsTable.getItems().addAll(patients);
////    }
//
//
//
//}
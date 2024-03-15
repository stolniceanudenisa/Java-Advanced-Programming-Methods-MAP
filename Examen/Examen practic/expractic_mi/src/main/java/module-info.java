module com.example.expractic_mi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.expractic_mi to javafx.fxml;
    opens com.example.expractic_mi.controller to javafx.fxml;

    exports com.example.expractic_mi;

    exports com.example.expractic_mi.domain;
    exports com.example.expractic_mi.controller;
    exports com.example.expractic_mi.service;
    exports com.example.expractic_mi.utils;
}
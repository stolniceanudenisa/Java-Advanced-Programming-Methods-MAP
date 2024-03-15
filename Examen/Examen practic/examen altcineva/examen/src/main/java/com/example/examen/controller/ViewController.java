package com.example.examen.controller;

import com.example.examen.domain.Tablou;
import com.example.examen.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;

public class ViewController {
    @FXML
    TextField textFieldTitlu;
    @FXML
    TextField textFieldPictor;
    @FXML
    TextField textFieldTematica;
    @FXML
    TextField textFieldCelebritate;
    @FXML
    public void add() {
        String titlu = textFieldTitlu.getText();
        String pictor = textFieldPictor.getText();
        String tematica = textFieldTematica.getText();
        Double celebritate = Double.valueOf(textFieldCelebritate.getText());
        Tablou tablou = new Tablou(Service.getInstance().idGenerator(),titlu,pictor,tematica,celebritate);
        Service.getInstance().create(tablou);
        textFieldTitlu.clear();
        textFieldCelebritate.clear();
        textFieldPictor.clear();
        textFieldTematica.clear();
    }
    @FXML
    public void showAllConsole() {
        List<Tablou> entities = Service.getInstance().read();
        entities.forEach(System.out::println);
    }
    @FXML
    public void subpunct2() {
        List<Tablou> tablouri = Service.getInstance().searchByTitlu("Nuferi Claude Monet");
        tablouri.forEach(System.out::println);
    }
    @FXML
    public void subpunct3() {
        List<Tablou> tablouri = Service.getInstance().filterByTematica("natura");
        tablouri.forEach(System.out::println);
    }
    @FXML
    public void subpunct4() {
        List<Tablou> tablouri = Service.getInstance().filterByTematicaAndCelebritate("natura",8.0);
        tablouri.forEach(System.out::println);
    }

    @FXML
    public void subpunct5a() {
        List<Tablou> tablouri = Service.getInstance().sortByPictorAndTitlu();
        tablouri.stream().map(t->t.getId() + " " + t.getPictor() + " " + t.getTitlu()).forEach(System.out::println);
    }

    @FXML
    public void subpunct5b() {
        List<Tablou> tablouri = Service.getInstance().sortByTematica();
        tablouri.stream().map(t->t.getTitlu() + " " + t.getPictor() + " " + t.getTematica()).forEach(System.out::println);
    }

    @FXML
    public void subpunct5c() {
        List<Tablou> tablouri = Service.getInstance().sortByCelebritateDescending();
        tablouri.stream().map(t->t.getId() + " " + t.getCelebritate()).forEach(System.out::println);
    }
}

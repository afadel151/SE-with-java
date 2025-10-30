package com.modules.ui;

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert; 
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.modules.entities.Student;
import com.modules.manager.PersonalManagment;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        PersonalManagment manager = new PersonalManagment();
        manager.retrieveEtudiants();

        Label titleLabel = new Label("Personnel Management GUI");

        ListView<Student> listView = new ListView<>();
        listView.getItems().addAll(manager.getAllStudents());
        listView.setCellFactory(lv -> new ListCell<Student>() {
            @Override
            protected void updateItem(Student item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + ", ID: " + item.getId() + ", Major: " + item.getMajor() + ", Year: " + item.getYear());
                }
            }
        });

        Button addButton = new Button("Add Student");
        addButton.setOnAction(e -> {
            Dialog<Student> dialog = new Dialog<>();

            dialog.setTitle("Add Student");
            dialog.setHeaderText("Enter Student Details");

            ButtonType addButtonType = new ButtonType("Add", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField nameField = new TextField();
            nameField.setPromptText("Name");
            TextField idField = new TextField();
            idField.setPromptText("ID");
            TextField majorField = new TextField();
            majorField.setPromptText("Major");
            TextField yearField = new TextField();
            yearField.setPromptText("Year");

            grid.add(new Label("Name:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("ID:"), 0, 1);
            grid.add(idField, 1, 1);
            grid.add(new Label("Major:"), 0, 2);
            grid.add(majorField, 1, 2);
            grid.add(new Label("Year:"), 0, 3);
            grid.add(yearField, 1, 3);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                    try {
                        String name = nameField.getText();
                        int id = Integer.parseInt(idField.getText());
                        String major = majorField.getText();
                        int year = Integer.parseInt(yearField.getText());
                        return new Student(name, id, major, year);
                    } catch (NumberFormatException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid ID or Year. Please enter numbers.");
                        alert.showAndWait();
                        return null;
                    }
                }
                return null;
            });

            Optional<Student> result = dialog.showAndWait();
            result.ifPresent(student -> {
                if (manager.ajouterEtudiant(student)) {
                    manager.sauveguarderEtudiants();
                    listView.getItems().setAll(manager.getAllStudents());
                }
            });
        });

        VBox layout = new VBox(10, titleLabel, listView, addButton);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 400, 400);

        stage.setTitle("User Interface");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(); 
    }
}
package com.pk.ems.auth;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthController {

    @FXML
    private TextField usernameField;// email

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> designationComboBox;

    @FXML
    private ComboBox<String> roleComboBox;

    public void initialize() {
        ObservableList<String> designations = FXCollections.observableArrayList(
            "Manager",
            "CEO",
            "Junior Software Developer",
            "Senior Software Developer"
        );
        designationComboBox.setItems(designations);
        designationComboBox.getSelectionModel().select(2);

        ObservableList<String> rolesList = FXCollections.observableArrayList(
            "Admin",
            "Employee"
        );
        roleComboBox.setItems(rolesList);
        roleComboBox.getSelectionModel().select(1);
    }
    

    @FXML
    public void signup() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println("Signing up with username: " + username + ", password: " + password);
    }

    @FXML
    public void cancel() {
        System.out.println("Signup cancelled.");
    }
}

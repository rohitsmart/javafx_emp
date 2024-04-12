package com.pk.ems.auth;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

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

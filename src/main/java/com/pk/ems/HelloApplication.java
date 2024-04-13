package com.pk.ems;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private boolean isLoggedIn = false;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        isLoggedIn = authenticateUser();

        if (isLoggedIn) {
            showDashboardScreen();
        } else {
            showLoginScreen();
        }
    }

    private void showLoginScreen() {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                boolean isAuthenticated = authenticateUser(username, password);

                if (isAuthenticated) {
                    isLoggedIn = true;
                    showDashboardScreen();
                } else {
                    System.out.println("Invalid username or password");
                }
            }
        });

        VBox loginVBox = new VBox(10);
        loginVBox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);
        loginVBox.setAlignment(Pos.CENTER);
        loginVBox.setPadding(new Insets(20));

        Scene loginScene = new Scene(loginVBox, 300, 200);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("EMS Login");
        primaryStage.show();
    }

    private void showDashboardScreen() {
        Label welcomeLabel = new Label("Welcome!");
        Button logoutButton = new Button("Logout");

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isLoggedIn = false;
                showLoginScreen();
            }
        });

        HBox dashboardHBox = new HBox(10);
        dashboardHBox.getChildren().addAll(welcomeLabel, logoutButton);
        dashboardHBox.setAlignment(Pos.CENTER);
        dashboardHBox.setPadding(new Insets(20));

        Scene dashboardScene = new Scene(dashboardHBox, 400, 300);
        primaryStage.setScene(dashboardScene);
        primaryStage.setTitle("EMS Dashboard");
        primaryStage.show();
    }

    private boolean authenticateUser() {
        
        return true;
    }

    private boolean authenticateUser(String username, String password) {

        return username.equals("admin") && password.equals("password");
    }

    public static void main(String[] args) {
        launch();
    }
}

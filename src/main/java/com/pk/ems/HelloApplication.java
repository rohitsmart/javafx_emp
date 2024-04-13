package com.pk.ems;

import java.io.IOException;

import com.pk.ems.security.WifiDetails;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signup-view.fxml"));
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        Scene scene = new Scene(fxmlLoader.load(), screenWidth, screenHeight);

        String bootstrapCSSPath = "/com/pk/ems/assets/css/bootstrap.min.css";
        scene.getStylesheets().add(getClass().getResource(bootstrapCSSPath).toExternalForm());

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/com/pk/ems/assets/js/bootstrap.bundle.min.js").toExternalForm());


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        WifiDetails wifiDetails= new WifiDetails();
        System.out.println(wifiDetails.getWifiSSID());
        wifiDetails.printRouterDetails();
    }

    public static void main(String[] args) {
        launch();
    }
}

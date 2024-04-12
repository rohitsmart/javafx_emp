// package com.pk.ems;

// import java.io.IOException;

// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

// public class HelloApplication extends Application {
//     @SuppressWarnings("exports")
//     @Override
//     public void start(Stage stage) throws IOException {
//         FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//         Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//         stage.setTitle("Hello!");
//         stage.setScene(scene);
//         stage.show();
//     }

//     public static void main(String[] args) {
//         launch();
//     }
// }


package com.pk.ems;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
                
        Scene scene = new Scene(fxmlLoader.load(), screenWidth, screenHeight);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        try {
            String wifiName = getWifiSSID();
            System.out.println("Connected WiFi Network: " + wifiName);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private static String getWifiSSID() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface iface = interfaces.nextElement();
            if (iface.isUp() && !iface.isLoopback()) {
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (!addr.isLoopbackAddress() && iface.getDisplayName().startsWith("w")) {
                        return iface.getDisplayName();
                    }
                }
            }
        }
        return "Unknown";
    }
}

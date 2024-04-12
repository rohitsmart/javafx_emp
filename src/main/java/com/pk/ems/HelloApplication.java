package com.pk.ems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            printRouterDetails();

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

 private static String getWifiSSID() throws IOException {
        Process process = Runtime.getRuntime().exec("iwgetid -r");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            return reader.readLine().trim();
        }
    }

    private static void printRouterDetails() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface iface = interfaces.nextElement();
            if (iface.isUp() && !iface.isLoopback()) {
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (!addr.isLoopbackAddress() && iface.getDisplayName().startsWith("w")) {
                        System.out.println("Router IP Address: " + addr.getHostAddress());
                        System.out.println("Router Host Name: " + addr.getHostName());
                        byte[] mac = iface.getHardwareAddress();
                        StringBuilder macAddress = new StringBuilder();
                        if (mac != null) {
                            for (int i = 0; i < mac.length; i++) {
                                macAddress.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
                            }
                            System.out.println("Router MAC Address: " + macAddress.toString());
                        }
                        break;
                    }
                }
            }
        }
    }
}


/*
 * Connected WiFi Network: PerfectKode.com_5G
Router IP Address: fe80:0:0:0:90e:873a:fbea:5e5e%wlp0s20f3
Router Host Name: fe80:0:0:0:90e:873a:fbea:5e5e%wlp0s20f3
Router MAC Address: 80:38:FB:C7:9F:6B
Connected WiFi Network: PerfectKode.com
Router IP Address: 2401:4900:1c5d:2f1c:126c:166f:c227:f7b1%wlp0s20f3
Router Host Name: 2401:4900:1c5d:2f1c:126c:166f:c227:f7b1%wlp0s20f3
Router MAC Address: 80:38:FB:C7:9F:6B

 */

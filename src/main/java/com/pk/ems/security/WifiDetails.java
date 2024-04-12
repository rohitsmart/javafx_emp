package com.pk.ems.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class WifiDetails {

    public String getWifiSSID() throws IOException {
        Process process = Runtime.getRuntime().exec("iwgetid -r");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            return reader.readLine().trim();
        }
    }

    public void printRouterDetails() throws SocketException {
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

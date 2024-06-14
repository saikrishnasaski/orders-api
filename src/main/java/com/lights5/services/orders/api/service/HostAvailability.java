package com.lights5.services.orders.api.service;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;

@Slf4j
public class HostAvailability {

    private HostAvailability() {
        // to prevent instantiation from other classes.
    }

    public static boolean isAvailable(String hostName, int port) {
        SocketAddress socketAddress = new InetSocketAddress(hostName, port);

        try (Socket socket = new Socket()) {

            socket.connect(socketAddress, 5000);
        } catch (IOException e) {

            log.error("Application Health Check Failed due to service unavailability {}", e.getMessage());
            return false;
        }
        return true;
    }
}

package com.hit.model;

import java.io.*;
import java.net.Socket;

/**
 *
 */
public class CacheUnitClient {
    private int port;
    private String host;

    /**
     * Default Ctor - Set the host as LocalHost and the Port 12345
     */
    public CacheUnitClient() {
        host = "127.0.0.1";
        port = 12345;
    }

    /**
     * Ctor - enable to receive the port and the host of the service
     * @param port
     * @param host
     */
    public CacheUnitClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    /**
     * Send a Request to a service (Server)
     * @param request - The desired request
     * @return - The response from the service
     */
    public String send(String request) {
        String response = "This";

        try (Socket socket = new Socket(host, port);
             BufferedReader reciever = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             DataOutputStream sender = new DataOutputStream(socket.getOutputStream())) {

            sender.writeBytes(request + "\n");
            response = reciever.readLine();

        } catch (IOException e) {
            System.out.println("can't connect.");
            response = "Something went wrong.";
        }

        return response;
    }
}

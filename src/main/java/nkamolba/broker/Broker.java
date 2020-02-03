package nkamolba.broker;

import java.io.*;
import java.net.*;

public class Broker {

    static String ip = "localhost";
    static final int port = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(ip, port)) {
            InternalBroker internalBroker = new InternalBroker(socket);
            internalBroker.run();
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
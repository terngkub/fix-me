package nkamolba.market;

import java.io.*;
import java.net.*;


public class Market {

    static String ip = "localhost";
    static final int port = 5001;

    public static void main(String[] args) {
        try (Socket socket = new Socket(ip, port)) {
            InternalMarket internalMarket = new InternalMarket(socket);
            internalMarket.run();
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
        
    }
}
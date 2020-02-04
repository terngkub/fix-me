package nkamolba.router;

import java.io.*;
import java.net.*;

class MarketTask extends RouterTask implements Runnable {

    MarketTask(Socket socket, String id) {
        super(socket, id);
    }

    public void run() {
        System.out.println("Market [" + id + "] connected to the router");
        try {
            registerMarket();
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }

    private void registerMarket() throws IOException {
        String response = reader.readLine();
        System.out.println("Market [" + id + "] is registering its market name");
        RoutingTable.registerMarket(response, socket);
    }
}
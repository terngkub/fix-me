package nkamolba.router;

import java.io.*;
import java.net.*;

class BrokerTask extends RouterTask implements Runnable{

    BrokerTask(Socket socket, String id) {
        super(socket, id);
    }

    public void run() {
        System.out.println("New broker connected");
        try {
            while (socket.isConnected()) {

                String response = reader.readLine();
                if (response == null) {
                    break;
                }

                Socket marketSocket = RoutingTable.getMarketSocket("N");
                OutputStream marketOutput = marketSocket.getOutputStream();
                PrintWriter marketWriter = new PrintWriter(marketOutput, true);
                marketWriter.println(response);
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } finally {
            System.out.println("Connection close");
        }
    }

}
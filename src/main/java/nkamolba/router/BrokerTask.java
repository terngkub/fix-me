package nkamolba.router;

import java.io.*;
import java.net.*;
import nkamolba.util.SocketStream;

class BrokerTask extends SocketStream implements Runnable{

    Socket socket;

    BrokerTask(Socket socket) {
        super(socket);
        this.socket = socket;
    }

    public void run() {
        System.out.println("New broker connected");
        try {
            while (socket.isConnected()) {

                String response = reader.readLine();
                if (response == null) {
                    break;
                }

                System.out.println("Received message: " + response);
                writer.println("Router received message: " + response);
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } finally {
            System.out.println("Connection close");
        }
    }

}
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

class BrokerTask implements Runnable {

    Socket socket;

    BrokerTask(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("New broker connected");
        try {
            OutputStream output = socket. getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("Hello broker");
        } catch (IOException e) {
            
        }
    }

}
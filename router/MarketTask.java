import java.io.*;
import java.net.*;
import java.util.concurrent.*;

class MarketTask implements Runnable {

    Socket socket;

    MarketTask(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("New market connected");
        try {
            OutputStream output = socket. getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("Hello market");
        } catch (IOException e) {
            
        }
    }

}
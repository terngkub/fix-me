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
            while (socket.isConnected()) {
                
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                OutputStream output = socket. getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

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
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Router {

    public static void main(String[] args) {

        System.out.println("Starting router");

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            System.out.println("Router is listening on port 5000 for brokers");

            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(new BrokerTask(socket));
            }

        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }

        executorService.shutdown();
    }

}
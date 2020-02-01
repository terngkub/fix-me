import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Router {

    static int brokerPort = 5000;
    static int marketPort = 5001;

    public static void main(String[] args) {

        System.out.println("Starting router");

        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);

        Thread brokerThread = new Thread() {
            public void run() {
                try (ServerSocket brokerSocket = new ServerSocket(brokerPort)) {

                    System.out.println("Router is listening on port 5000 for brokers");

                    while (true) {
                        Socket socket = brokerSocket.accept();
                        executorService.submit(new BrokerTask(socket));
                    }

                } catch (IOException e) {
                    System.err.println("Server exception: " + e.getMessage());
                }
            }
        };

        Thread marketThread = new Thread() {
            public void run() {
                try (ServerSocket marketSocket = new ServerSocket(marketPort)) {

                    System.out.println("Router is listening on port 5001 for markets");

                    while (true) {
                        Socket socket = marketSocket.accept();
                        executorService.submit(new MarketTask(socket));
                    }

                } catch (IOException e) {
                    System.err.println("Server exception: " + e.getMessage());
                }
            }
        };


        brokerThread.start();
        marketThread.start();

        try {
            brokerThread.join();
            marketThread.join();
        } catch (InterruptedException e) {

        }

        executorService.shutdown();
    }

}
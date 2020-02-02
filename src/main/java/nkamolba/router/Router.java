import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.*;

class SocketThread<T extends Runnable> implements Runnable {

    int port;
    Class<T> taskClass;
    ExecutorService executorService;

    SocketThread(int port, Class<T> taskClass, ExecutorService executorService) {
        this.port = port;
        this.taskClass = taskClass;
        this.executorService = executorService;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Router is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Accecpt connection");
                try {
                    T task = taskClass.getDeclaredConstructor(Socket.class).newInstance(socket);
                    executorService.submit(task);
                } catch (Exception e) {
                    System.out.println("Found some exception: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }
}

public class Router {

    static final int brokerPort = 5000;
    static final int marketPort = 5001;
    static HashMap<Integer, Socket> routingTable = new HashMap<Integer, Socket>();
    static int  socketId = 0;

    public static void main(String[] args) {

        System.out.println("Starting router");

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available pool size = " + cores);
        ExecutorService executorService = Executors.newFixedThreadPool(cores);

        SocketThread brokerThread = new SocketThread(brokerPort, BrokerTask.class, executorService);
        SocketThread marketThread = new SocketThread(marketPort, MarketTask.class, executorService);

        executorService.submit(brokerThread);
        marketThread.run();

        executorService.shutdown();
    }

}
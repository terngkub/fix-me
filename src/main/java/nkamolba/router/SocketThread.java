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
            System.out.println("Listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                assignUniqueId(socket);
                submitTask(socket);
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }

    private void assignUniqueId(Socket socket) {
        try {
            String id = RoutingTable.registerSocket(socket);
            System.out.println("Received new connection, assigned ID: " + id);

            OutputStream output = socket. getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(id);
        } catch (IOException e) {
            System.err.println();
        }
    }

    private void submitTask(Socket socket) {
        try {
            T task = taskClass.getDeclaredConstructor(Socket.class).newInstance(socket);
            executorService.submit(task);
        } catch (Exception e) {
            System.out.println("Found some exception: " + e.getMessage());
        }
    }
}
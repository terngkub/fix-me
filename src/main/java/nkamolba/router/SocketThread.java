package nkamolba.router;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

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
                String id = assignUniqueId(socket);
                submitTask(socket, id);
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }

    private String assignUniqueId(Socket socket) {
        String id = "";
        try {
            id = RoutingTable.registerSocket(socket);
            System.out.println("Received new connection, assigned ID: " + id);

            OutputStream output = socket. getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(id);
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }

        return id;
    }

    private void submitTask(Socket socket, String id) {
        try {
            T task = taskClass.getDeclaredConstructor(Socket.class, String.class).newInstance(socket, id);
            executorService.submit(task);
        } catch (Exception e) {
            System.out.println("Found some exception: " + e.getMessage());
        }
    }
}
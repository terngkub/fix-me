import java.io.*;
import java.net.*;

public class Broker {

    static final int port = 5000;
    static String ip = "localhost";
    static String id;

    static void getUniqueID(Socket socket) {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String response = reader.readLine();
            id = response;
            System.out.println("Receive unique ID from the router: " + id);

        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(ip, port)) {
            getUniqueID(socket);
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }

}
import java.io.*;
import java.net.*;

public class Broker {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String response = reader.readLine();
            System.out.println(response);
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }

}
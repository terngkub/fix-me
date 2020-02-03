package nkamolba.broker;

import java.net.Socket;
import java.io.*;
import nkamolba.util.SocketStream;

class InternalBroker extends SocketStream {

    private String id;

    public InternalBroker(Socket socket) {
        super(socket);
    }
        
    public void run() {
        getUniqueID();
        runConsole();
    }

    private void getUniqueID() {
        try {
            String response = reader.readLine();
            id = response;
            System.out.println("Receive unique ID from the router: " + id);

        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }

    private void runConsole() {
        Console console = System.console();
        while (true) {
            String line = console.readLine("Enter command: ");

            if (line.equals("exit")) {
                System.out.println("Closing the router");
                break;
            }
            
            try {

                writer.println(line);
                System.out.println("Sent message to router: " + line);

                String response = reader.readLine();
                System.out.println("Receive message from router: " + response);

            } catch (IOException e) {
                System.out.println("I/O error: " + e.getMessage());
            }
        }
    }

}

package nkamolba.broker;

import java.net.Socket;
import java.io.*;
import nkamolba.util.RouterClient;;

class InternalBroker extends RouterClient {

    public InternalBroker(Socket socket) {
        super(socket);
    }
        
    public void run() {
        getUniqueId();
        runConsole();
    }

    private void runConsole() {
        Console console = System.console();
        while (true) {
            // getting user input
            String line = console.readLine("Enter command: ");

            // handle exit
            if (line.equals("exit")) {
                System.out.println("Closing the router");
                break;
            }

            // parsing user input

            // sending order
            writer.println(line);
            System.out.println("Sent message to router: " + line);

            // waiting for response
            try {
                String response = reader.readLine();
                System.out.println("Got message: " + response);
            } catch (IOException e) {
                System.err.println("I/O error: " + e.getMessage());
            }
        }
    }

}

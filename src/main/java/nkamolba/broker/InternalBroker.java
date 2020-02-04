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
            String line = console.readLine("Enter command: ");

            if (line.equals("exit")) {
                System.out.println("Closing the router");
                break;
            }
            
            writer.println(line);
            System.out.println("Sent message to router: " + line);
        }
    }

}

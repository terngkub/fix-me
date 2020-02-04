package nkamolba.market;

import java.io.IOException;
import java.net.Socket;
import nkamolba.util.RouterClient;

class InternalMarket extends RouterClient {

    String name;

    public InternalMarket(Socket socket, String name) {
        super(socket);
        this.name = name;
    }

    public void run() {
        System.out.println("Starting market: " + name);
        getUniqueId();
        registerMarket();
        runDaemon();
    }

    private void registerMarket() {
        System.out.println("Sending market name to the router");
        writer.println(name);
    }
    
    private void runDaemon() {
        while (true) {
            try {

                // receive message
                String inMessage = reader.readLine();
                if (inMessage == null) break;
                System.out.println("Got incoming message: " + inMessage);

                // parsing

                // processing

                // response
                String outMessage = "I got your message";
                writer.println(outMessage);

            } catch (IOException e) {
                System.err.println("I/O error: " + e.getMessage());
                break;
            }
        }
    }
}

package nkamolba.util;

import java.net.Socket;
import java.io.*;

public class RouterClient extends SocketStream {

    protected String id;

    public RouterClient(Socket socket) {
        super(socket);
    }

    protected void getUniqueId() {
        try {
            String response = reader.readLine();
            id = response;
            System.out.println("Receive unique ID from the router: " + id);

        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
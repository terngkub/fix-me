package nkamolba.util;

import java.io.*;
import java.net.Socket;

public class SocketStream {

    protected BufferedReader reader;
    protected PrintWriter writer;

    public SocketStream(Socket socket) {
        try {

            InputStream input = socket.getInputStream();
            this.reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
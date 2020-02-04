package nkamolba.router;

import java.net.Socket;
import nkamolba.util.SocketStream;

abstract class RouterTask extends SocketStream {

    protected Socket socket;
    protected String id;

    RouterTask(Socket socket, String id) {
        super(socket);
        this.socket = socket;
        this.id = id;
    }

    public abstract void run();
}
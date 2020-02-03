package nkamolba.market;

import java.net.Socket;
import nkamolba.util.RouterClient;

class InternalMarket extends RouterClient {

    public InternalMarket(Socket socket) {
        super(socket);
    }

    public void run() {
        getUniqueId();
    }
}

package nkamolba.router;

import java.net.Socket;
import java.util.HashMap;

class RoutingTable {
    static private int id = 0;
    static private HashMap<String, Socket> routingTable = new HashMap<String, Socket>();
    static private HashMap<String, Socket> marketTable = new HashMap<String, Socket>();

    static synchronized String registerSocket(Socket socket) {
        String uniqueId = String.format("%06d", id);
        id += 1;
        routingTable.put(uniqueId, socket);
        return uniqueId;
    }

    static synchronized void registerMarket(String marketName, Socket socket) {
        marketTable.put(marketName, socket);
        System.out.println("Registered market '" + marketName + "'");
    }
}
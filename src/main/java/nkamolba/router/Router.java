package nkamolba.router;

import java.util.concurrent.*;

public class Router {

    static final int brokerPort = 5000;
    static final int marketPort = 5001;

    public static void main(String[] args) {

        System.out.println("Starting router");

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available pool size: " + cores);
        ExecutorService executorService = Executors.newFixedThreadPool(cores);

        SocketThread<BrokerTask> brokerThread = new SocketThread<BrokerTask>(brokerPort, BrokerTask.class, executorService);
        SocketThread<MarketTask> marketThread = new SocketThread<MarketTask>(marketPort, MarketTask.class, executorService);

        executorService.submit(brokerThread);
        marketThread.run();

        executorService.shutdown();
    }

}
package org.aburavov.otus.java.basic.hw13;

import org.aburavov.otus.java.basic.hw13.network.Client;
import org.aburavov.otus.java.basic.hw13.network.Server;
import org.aburavov.otus.java.basic.hw13.controllers.ServerController;

public class Main {
    private static final int port = 8091;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, this is HW13");

        ServerController serverController = new ServerController();

        Server server = new Server(serverController, port);
        Thread serverThread = new Thread(() -> {
            try {
                server.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.start();

        DemoClient demoClient = new DemoClient();
        demoClient.Run(port);

        server.stop();
        serverThread.join();

        System.out.println("DONE");
    }
}

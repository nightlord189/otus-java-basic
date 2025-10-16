package org.aburavov.otus.java.basic.hw13.network;

import org.aburavov.otus.java.basic.hw13.controllers.ServerController;
import org.aburavov.otus.java.basic.hw13.protocol.Mapper;
import org.aburavov.otus.java.basic.hw13.protocol.Parameter;
import org.aburavov.otus.java.basic.hw13.protocol.ProtocolException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

public class Server {
    private final ServerController serverController;
    private final int port;
    private ServerSocket serverSocket;
    private volatile boolean running;

    public boolean isRunning() {
        return running;
    }

    public Server(ServerController sc, int port) {
        this.port = port;
        this.serverController = sc;
    }

    public void run() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server: running");
        running = true;

        while (running) {
            try {
                Socket client = serverSocket.accept();
                handleClientAsync(client);
            } catch (SocketException e) {
                if (running) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Server: stopped");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientAsync(Socket client) {
        new Thread(() -> handleClient(client), "client-handler").start();
    }

    private void handleClient(Socket client) {
        try (client;
             DataInputStream dis = new DataInputStream(client.getInputStream());
             DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        ) {
            System.out.println("Server: new client connected: " + client.getRemoteSocketAddress());

            while (true) {
                String userInput = dis.readUTF();
                handleClientMessage(userInput, dos);
            }
        } catch (EOFException ex) {
            System.out.println("Server: client disconnected: " + client.getRemoteSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleClientMessage(String message, DataOutputStream dos) throws IOException {
        try {
            System.out.println("Server: received raw message from client: " + message);
            Map<Parameter, String> decodedMessage = Mapper.decode(message);
            Map<Parameter, String> response = serverController.handle(decodedMessage);
            String encodedResponse = Mapper.encode(response);
            System.out.println("Server: sending response to client: " + encodedResponse);
            dos.writeUTF(encodedResponse);
        } catch (ProtocolException e) {
            System.out.println("Server: protocol error on handling client message: " + e);
        }
    }
}

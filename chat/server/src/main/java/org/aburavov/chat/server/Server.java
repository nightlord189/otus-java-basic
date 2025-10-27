package org.aburavov.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private final int port;

    private final List<ClientHandler> clients;

    public Server(int port) {
        this.port = port;
        clients = new CopyOnWriteArrayList<>();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запустился на порту: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                subscribe(new ClientHandler(socket, this));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        System.out.println("Клиент " + clientHandler.getUsername() + " отключился");
        clients.remove(clientHandler);
    }

    public void broadcastMessage(String message) {
        for (ClientHandler c : clients) {
            c.sendMsg(message);
        }
    }

    public void sendMessageToClient(String addressee, String author, String message) {
        ClientHandler client = clients.stream()
                .filter(x -> x.getUsername().equals(addressee))
                .findFirst()
                .orElse(null);
        if (client == null) {
            System.out.println("Не могу отправить личное сообщение клиенту " + addressee + ": такой клиент не подключен");
            return;
        }
        client.sendMsg(author + ": " + message);
        System.out.println("Отправлено личное сообщение " + addressee + " от " + author);
    }

}

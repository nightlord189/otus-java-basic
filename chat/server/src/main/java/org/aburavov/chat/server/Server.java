package org.aburavov.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private final int port;

    private final List<ClientHandler> clients;
    private final AuthenticatedProvider authenticatedProvider;

    public Server(int port) {
        this.port = port;
        clients = new CopyOnWriteArrayList<>();
        authenticatedProvider = new InMemoryAuthenticatedProvider(this);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запустился на порту: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket, this);
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

    public void broadcastMessage(String username, String message) {
        for (ClientHandler c : clients) {
            c.sendMsg(ConsoleColors.CYAN_BOLD + username + ": " + ConsoleColors.RESET + message);
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

    public boolean isUsernameBusy(String username) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean kickUser(String username) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(username)) {
                c.disconnect();
                return true;
            }
        }
        return false;
    }

    public AuthenticatedProvider getAuthenticatedProvider() {
        return authenticatedProvider;
    }
}

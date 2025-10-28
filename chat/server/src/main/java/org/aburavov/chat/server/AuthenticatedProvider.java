package org.aburavov.chat.server;

public interface AuthenticatedProvider {
    void initialize();

    boolean authenticate(ClientHandler clientHandler, String login, String password);

    boolean register(ClientHandler clientHandler, String login, String password);
}

package org.aburavov.chat.server.auth;

import org.aburavov.chat.server.ClientHandler;

public interface AuthenticatedProvider {
    void initialize();

    boolean authenticate(ClientHandler clientHandler, String login, String password);

    boolean register(ClientHandler clientHandler, String login, String password);
}

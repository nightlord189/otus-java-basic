package org.aburavov.chat.server.auth;

import org.aburavov.chat.server.ClientHandler;
import org.aburavov.chat.server.Server;
import org.aburavov.chat.server.User;

public abstract class BaseAuthenticatedProvider implements AuthenticatedProvider {
    protected final Server server;

    public BaseAuthenticatedProvider(Server server) {
        this.server = server;
    }

    protected abstract boolean isLoginAlreadyExists(String login);

    protected abstract User getUserByLoginAndPassword(String login, String password);

    protected abstract void addUser(User user);

    @Override
    public boolean authenticate(ClientHandler clientHandler, String login, String password) {
        User user = getUserByLoginAndPassword(login, password);
        if (user == null) {
            clientHandler.sendMsg("Некорректный логин/пароль");
            return false;
        }
        if (server.isUsernameBusy(login)) {
            clientHandler.sendMsg("Указанная учетная запись уже используется");
            return false;
        }
        server.subscribe(clientHandler);
        clientHandler.setUser(user);
        clientHandler.sendMsg("/authok " + login);
        return true;
    }

    @Override
    public boolean register(ClientHandler clientHandler, String login, String password) {
        String loginValidationErr = Validator.validateLogin(login);
        if (loginValidationErr != null) {
            clientHandler.sendMsg(loginValidationErr);
            return false;
        }

        String passwordValidationErr = Validator.validateLogin(password);
        if (passwordValidationErr != null) {
            clientHandler.sendMsg(passwordValidationErr);
            return false;
        }
        
        if (isLoginAlreadyExists(login)) {
            clientHandler.sendMsg("Такой логин уже занят");
            return false;
        }

        User user = new User(login, password);

        addUser(user);
        server.subscribe(clientHandler);

        clientHandler.setUser(user);
        clientHandler.sendMsg("/regok " + login);
        return true;
    }
}

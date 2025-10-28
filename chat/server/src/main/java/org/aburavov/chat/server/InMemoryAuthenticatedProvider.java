package org.aburavov.chat.server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryAuthenticatedProvider implements AuthenticatedProvider {
    private class User {
        private String login;
        private String password;

        public User(String login, String password) {
            this.login = login;
            this.password = password;
        }
    }

    private List<User> users;
    private Server server;

    public InMemoryAuthenticatedProvider(Server server) {
        this.server = server;
        this.users = new CopyOnWriteArrayList<>();
        users.add(new User("qwe", "qwe"));
        users.add(new User("asd", "asd"));
        users.add(new User("zxc", "zxc"));
    }

    private boolean isLoginAlreadyExists(String login) {
        for (User u : users) {
            if (u.login.equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }

    private User getUserByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.login.equalsIgnoreCase(login) && u.password.equals(password)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void initialize() {
        System.out.println("used InMemoryAuthenticatedProvider");
    }

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
        clientHandler.setUsername(login);
        clientHandler.sendMsg("/authok " + login);
        return true;
    }

    @Override
    public boolean register(ClientHandler clientHandler, String login, String password) {
        if (login.length() < 3) {
            clientHandler.sendMsg("Логин должен содержать 3+ символов");
            return false;
        }
        if (!login.toLowerCase().matches("[a-z0-9]+")) {
            clientHandler.sendMsg("Логин должен состоять только из латинских букв и цифр");
            return false;
        }
        if (password.length() < 3) {
            clientHandler.sendMsg("Пароль должен содержать 3+ символов");
            return false;
        }
        if (isLoginAlreadyExists(login)) {
            clientHandler.sendMsg("Такой логин уже занят");
            return false;
        }
        users.add(new User(login, password));
        server.subscribe(clientHandler);
        clientHandler.setUsername(login);
        clientHandler.sendMsg("/regok " + login);
        return true;
    }
}

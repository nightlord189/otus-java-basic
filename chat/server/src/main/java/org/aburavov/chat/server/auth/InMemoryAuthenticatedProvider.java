package org.aburavov.chat.server.auth;

import org.aburavov.chat.server.Server;
import org.aburavov.chat.server.User;
import org.aburavov.chat.server.UserRole;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryAuthenticatedProvider extends BaseAuthenticatedProvider implements AuthenticatedProvider {
    private final List<User> users;

    public InMemoryAuthenticatedProvider(Server server) {
        super(server);
        this.users = new CopyOnWriteArrayList<>();
        users.add(new User("admin1", "password1", UserRole.ADMIN));
        users.add(new User("qwe", "qwe"));
        users.add(new User("asd", "asd"));
        users.add(new User("zxc", "zxc"));
    }

    @Override
    protected boolean isLoginAlreadyExists(String login) {
        for (User u : users) {
            if (u.getLogin().equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected User getUserByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.getLogin().equalsIgnoreCase(login) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    @Override
    protected void addUser(User user) {
        users.add(user);
    }

    @Override
    public void initialize() {
        System.out.println("used InMemoryAuthenticatedProvider");
    }
}

package org.aburavov.chat.server;

public class User {
    private String login;
    private String password;
    private UserRole role = UserRole.USER;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }
}

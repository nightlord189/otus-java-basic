package org.aburavov.chat.server.auth;

import org.aburavov.chat.server.Server;
import org.aburavov.chat.server.User;
import org.aburavov.chat.server.UserRole;

import java.sql.*;

public class DbAuthenticatedProvider extends BaseAuthenticatedProvider implements AuthenticatedProvider {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/chat";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password";

    private static final String GET_USER_QUERY = "SELECT login, password_hash, role FROM users WHERE login = ? LIMIT 1";
    private static final String INSERT_USER_QUERY = "INSERT INTO users (login, password_hash, role) VALUES (?, ?, ?::user_role)";

    private Connection connection;

    public DbAuthenticatedProvider(Server server) {
        super(server);
    }

    @Override
    public void initialize() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean isLoginAlreadyExists(String login) {
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_QUERY)) {
            ps.setString(1, login);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected User getUserByLoginAndPassword(String login, String password) {
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_QUERY)) {
            ps.setString(1, login);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String passwordHash = rs.getString(2);
                    UserRole role = UserRole.valueOf(rs.getString(3));

                    String hashedPassword = Utils.sha256(password);

                    if (!passwordHash.equals(hashedPassword)) {
                        return null;
                    }

                    return new User(login, password, role);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void addUser(User user) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER_QUERY)) {
            String hashedPassword = Utils.sha256(user.getPassword());

            ps.setString(1, user.getLogin());
            ps.setString(2, hashedPassword);
            ps.setString(3, user.getRole().name());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

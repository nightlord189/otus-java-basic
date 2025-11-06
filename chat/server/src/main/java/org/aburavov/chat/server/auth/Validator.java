package org.aburavov.chat.server.auth;

public class Validator {
    public static String validateLogin(String login) {
        if (login.length() < 3) {
            return "Логин должен содержать 3+ символов";
        }
        if (!login.toLowerCase().matches("[a-z0-9]+")) {
            return "Логин должен состоять только из латинских букв и цифр";
        }
        return null;
    }

    public static String validatePassword(String password) {
        if (password.length() < 3) {
            return "Пароль должен содержать 3+ символов";
        }
        return null;
    }
}

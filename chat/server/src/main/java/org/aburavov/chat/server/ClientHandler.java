package org.aburavov.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;

    private String username;
    private boolean authenticated;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            System.out.println("Клиент подключился " + socket.getPort());
            try {
                //Цикл аутентификации
                while (true) {
                    sendMsg("Перед работой с чатом необходимо выполнить аутентификацию " +
                            ConsoleColors.GREEN_BOLD + "'/auth login password'" + ConsoleColors.RESET +
                            " или регистрацию " +
                            ConsoleColors.GREEN_BOLD + "'/reg login password'" + ConsoleColors.RESET);

                    String message = in.readUTF();

                    if (message.startsWith("/")) {
                        if (message.startsWith("/exit")) {
                            sendMsg("/exitok");
                            break;
                        }
                        // /auth login password
                        if (message.startsWith("/auth ")) {
                            String[] token = message.split(" ");
                            if (token.length != 3) {
                                sendMsg("Неверный формат команды /auth");
                                continue;
                            }
                            if (server.getAuthenticatedProvider()
                                    .authenticate(this, token[1], token[2])) {
                                authenticated = true;
                                sendMsg("Вы подключились с ником: " + username);
                                break;
                            }
                            continue;
                        }
                        // /reg login password
                        if (message.startsWith("/reg ")) {
                            String[] token = message.split(" ");
                            if (token.length != 3) {
                                sendMsg("Неверный формат команды /reg");
                                continue;
                            }
                            if (server.getAuthenticatedProvider()
                                    .register(this, token[1], token[2])) {
                                authenticated = true;
                                sendMsg("Вы подключились с ником: " + username);
                                break;
                            }
                        }
                    }
                }
                //Цикл работы
                while (authenticated) {
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.startsWith("/exit")) {
                            sendMsg("/exitok");
                            break;
                        }
                    } else if (message.startsWith("/w ")) {
                        String[] splitted = message.split(" ", 3);
                        if (splitted.length != 3) {
                            System.out.println("Не могу распарсить команду: " + message);
                            continue;
                        }
                        server.sendMessageToClient(splitted[1], username, splitted[2]);
                    } else {
                        server.broadcastMessage(username, message);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                disconnect();
            }
        }).start();
    }

    public void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void disconnect() {
        server.unsubscribe(this);
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

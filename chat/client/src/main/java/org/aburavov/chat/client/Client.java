package org.aburavov.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {
        Scanner sc = new Scanner(System.in);
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        String message = in.readUTF();
                        if (message.startsWith("/")) {
                            if (message.startsWith("/exitok")) {
                                break;
                            }
                            if (message.startsWith("/authok ")) {
                                System.out.println("Удалось успешно войти в чат под именем пользователя " +
                                        message.split(" ")[1]);
                            }
                            if (message.startsWith("/regok ")) {
                                System.out.println("Удалось успешно пройти регистрацию под ником " +
                                        message.split(" ")[1]);
                            }

                        } else {
                            System.out.println(message);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    disconnect();
                }
            }).start();

            while (true) {
                String message = sc.nextLine();
                out.writeUTF(message);
                if (message.startsWith("/exit")) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
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

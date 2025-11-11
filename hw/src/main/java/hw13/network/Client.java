package hw13.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private final String host;
    private final int port;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
        System.out.println("Client: connected");
    }

    public void send(String message) throws IOException {
        dos.writeUTF(message);
        dos.flush();
        System.out.println("Client: sent message: " + message);
    }

    public String receive() throws IOException {
        String input = dis.readUTF();
        System.out.println("Client: received message: " + input);
        return input;
    }

    public void disconnect() {
        if (socket == null || socket.isClosed()) {
            return;
        }
        try {
            socket.close();
            System.out.println("Client: disconnected");
        } catch (IOException e) {
            System.out.println("Client: disconnect error: " + e);
        }
    }
}

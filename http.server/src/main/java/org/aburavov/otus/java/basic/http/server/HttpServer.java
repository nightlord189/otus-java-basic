package org.aburavov.otus.java.basic.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private final static Logger logger = LogManager.getLogger(HttpServer.class.getName());

    private final int port;
    private final Dispatcher dispatcher;
    private final ThreadPool threadPool;

    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
        this.threadPool = new ThreadPool(5);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Сервер запущен на порту: {}", port);

            while (true) {
                Socket socket = serverSocket.accept();
                threadPool.execute(() -> {
                    handleConnection(socket);
                });
            }
        } catch (IOException e) {
            logger.error("Server start error: {}", e.toString());
        }
    }

    private void handleConnection(Socket socket) {
        try (socket) {
            try {
                byte[] buffer = new byte[8192];
                int n = socket.getInputStream().read(buffer);
                if (n < 1) {
                    return;
                }
                String rawRequest = new String(buffer, 0, n);
                HttpRequest request = new HttpRequest(rawRequest);

                logger.info(request.getRoutingKey());

                dispatcher.execute(request, socket.getOutputStream());
            } catch (IOException e) {
                logger.error("Handle connection error: {}", e.toString());
            }
        } catch (IOException e) {
            logger.error("handleConnection error: {}", e.toString());
        }
    }
}

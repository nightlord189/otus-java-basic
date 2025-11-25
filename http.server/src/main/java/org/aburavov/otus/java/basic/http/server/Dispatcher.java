package org.aburavov.otus.java.basic.http.server;

import org.aburavov.otus.java.basic.http.server.exceptions_handling.BadRequestException;
import org.aburavov.otus.java.basic.http.server.processors.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private final static Logger logger = LogManager.getLogger(Dispatcher.class.getName());

    private Map<String, RequestProcessor> routes;
    private RequestProcessor defaultNotFoundProcessor;

    public Dispatcher() {
        routes = new HashMap<>();
        routes.put("GET /hello", new HelloWorldProcessor());
        routes.put("POST /hello", new PostHelloWorldProcessor());
        routes.put("GET /add", new CalculatorProcessor());
        defaultNotFoundProcessor = new DefaultNotFoundProcessor();
    }

    public void execute(HttpRequest request, OutputStream output) throws IOException {
        if (!routes.containsKey(request.getRoutingKey())) {
            logger.warn("Not found: {}", request.getRoutingKey());
            defaultNotFoundProcessor.execute(request, output);
            return;
        }
        try {
            routes.get(request.getRoutingKey()).execute(request, output);
        } catch (BadRequestException e) {
            logger.error("Bad request: {}", String.valueOf(e));

            String response = "" +
                    "HTTP/1.1 400 Bad Request\r\n" +
                    "Content-Type: text/html;charset=utf-8\r\n" +
                    "\r\n" +
                    "<html><body><h1>BAD REQUEST: " + e.getMessage() + "</h1></body></html>";
            output.write(response.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            logger.error("Exception on processing request: {}", String.valueOf(e));

            String response = "" +
                    "HTTP/1.1 500 Internal Server Error\r\n" +
                    "Content-Type: text/html;charset=utf-8\r\n" +
                    "\r\n" +
                    "<html><body><h1>ОЙ</h1></body></html>";
            output.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}

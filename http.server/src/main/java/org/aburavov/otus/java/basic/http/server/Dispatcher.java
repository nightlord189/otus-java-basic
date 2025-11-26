package org.aburavov.otus.java.basic.http.server;

import org.aburavov.otus.java.basic.http.server.application.CreateItemsProcessor;
import org.aburavov.otus.java.basic.http.server.application.GetItemsProcessor;
import org.aburavov.otus.java.basic.http.server.exceptions_handling.BadRequestException;
import org.aburavov.otus.java.basic.http.server.processors.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private Map<String, RequestProcessor> routes;
    private RequestProcessor defaultNotFoundProcessor;
    private RequestProcessor defaultStaticResourceProcessor;

    public Dispatcher() {
        routes = new HashMap<>();
        routes.put("GET /hello", new HelloWorldProcessor());
        routes.put("POST /hello", new PostHelloWorldProcessor());
        routes.put("GET /add", new CalculatorProcessor());
        routes.put("GET /shop/api/v1/items", new GetItemsProcessor());
        routes.put("POST /shop/api/v1/items", new CreateItemsProcessor());
        defaultNotFoundProcessor = new DefaultNotFoundProcessor();
        defaultStaticResourceProcessor = new DefaultStaticResourceProcessor();
    }

    public void execute(HttpRequest request, OutputStream output) throws IOException {
        if (Files.exists(Paths.get("static/", request.getUri().substring(1)))) {
            defaultStaticResourceProcessor.execute(request, output);
            return;
        }
        if (!routes.containsKey(request.getRoutingKey())) {
            defaultNotFoundProcessor.execute(request, output);
            return;
        }
        try {
            routes.get(request.getRoutingKey()).execute(request, output);
        } catch (BadRequestException e) {
            String response = "" +
                    "HTTP/1.1 400 Bad Request\r\n" +
                    "Content-Type: text/html;charset=utf-8\r\n" +
                    "\r\n" +
                    "<html><body><h1>BAD REQUEST: " + e.getMessage() + "</h1></body></html>";
            output.write(response.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            String response = "" +
                    "HTTP/1.1 500 Internal Server Error\r\n" +
                    "Content-Type: text/html;charset=utf-8\r\n" +
                    "\r\n" +
                    "<html><body><h1>ОЙ</h1></body></html>";
            output.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}

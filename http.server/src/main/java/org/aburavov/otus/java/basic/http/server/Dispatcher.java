package org.aburavov.otus.java.basic.http.server;

import org.aburavov.otus.java.basic.http.server.application.CreateItemsProcessor;
import org.aburavov.otus.java.basic.http.server.application.DeleteItemsProcessor;
import org.aburavov.otus.java.basic.http.server.application.GetItemsProcessor;
import org.aburavov.otus.java.basic.http.server.exceptions_handling.HttpException;
import org.aburavov.otus.java.basic.http.server.processors.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private final Map<String, RequestProcessor> routes;
    private final RequestProcessor defaultNotFoundProcessor;
    private final RequestProcessor defaultStaticResourceProcessor;

    public Dispatcher() {
        routes = new HashMap<>();
        routes.put("GET /hello", new HelloWorldProcessor());
        routes.put("POST /hello", new PostHelloWorldProcessor());
        routes.put("GET /add", new CalculatorProcessor());
        routes.put("GET /shop/api/v1/items", new GetItemsProcessor());
        routes.put("POST /shop/api/v1/items", new CreateItemsProcessor());
        routes.put("DELETE /shop/api/v1/items", new DeleteItemsProcessor());
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
        } catch (HttpException e) {
            String response = new Response(
                    e.getStatus(),
                    "<html><body><h1>" + e.getStatus() + ": " + e.getMessage() + "</h1></body></html>",
                    ContentType.TEXT_HTML
            )
                    .build();
            output.write(response.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            String response = new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "<html><body><h1>" + e.getMessage() + "</h1></body></html>",
                    ContentType.TEXT_HTML
            )
                    .build();
            output.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}

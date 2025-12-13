package org.aburavov.otus.java.basic.http.server.processors;

import org.aburavov.otus.java.basic.http.server.ContentType;
import org.aburavov.otus.java.basic.http.server.HttpRequest;
import org.aburavov.otus.java.basic.http.server.HttpStatus;
import org.aburavov.otus.java.basic.http.server.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PostHelloWorldProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String response = new Response(
                HttpStatus.OK,
                "<html><body><h1>POST Hello World!</h1></body></html>",
                ContentType.TEXT_HTML).
                build();
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}

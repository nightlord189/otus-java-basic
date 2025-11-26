package org.aburavov.otus.java.basic.http.server.processors;

import org.aburavov.otus.java.basic.http.server.HttpRequest;
import org.aburavov.otus.java.basic.http.server.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DefaultNotFoundProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String response = new Response(
                404,
                "<html><body><h1>Page Not Found</h1></body></html>",
                Response.CONTENT_TYPE_TEXT_HTML).
                build();
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}

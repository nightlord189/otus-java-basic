package org.aburavov.otus.java.basic.http.server.processors;

import org.aburavov.otus.java.basic.http.server.HttpRequest;
import org.aburavov.otus.java.basic.http.server.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DefaultStaticResourceProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String filename = request.getUri().substring(1);
        Path filePath = Paths.get("static/", filename);
        byte[] fileData = Files.readAllBytes(filePath);

        Map<String, String> headers = Map.of("Content-Length", String.valueOf(fileData.length));

        String response = new Response(200, null, headers).build();
        
        output.write(response.getBytes(StandardCharsets.UTF_8));
        output.write(fileData);
    }
}

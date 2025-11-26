package org.aburavov.otus.java.basic.http.server.application;

import com.google.gson.Gson;
import org.aburavov.otus.java.basic.http.server.HttpRequest;
import org.aburavov.otus.java.basic.http.server.Response;
import org.aburavov.otus.java.basic.http.server.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CreateItemsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        Item item = gson.fromJson(request.getBody(), Item.class);
        ItemsStorage.createItem(item);
        String response = new Response(201, gson.toJson(item), Response.CONTENT_TYPE_APPLICATION_JSON).build();
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}

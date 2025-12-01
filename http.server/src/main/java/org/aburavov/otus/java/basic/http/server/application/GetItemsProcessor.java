package org.aburavov.otus.java.basic.http.server.application;

import com.google.gson.Gson;
import org.aburavov.otus.java.basic.http.server.ContentType;
import org.aburavov.otus.java.basic.http.server.HttpRequest;
import org.aburavov.otus.java.basic.http.server.HttpStatus;
import org.aburavov.otus.java.basic.http.server.Response;
import org.aburavov.otus.java.basic.http.server.exceptions_handling.HttpException;
import org.aburavov.otus.java.basic.http.server.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetItemsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        // GET /api/v1/items?id=10
        // GET /api/v1/items
        Gson gson = new Gson();
        String response;

        Long id = Util.parseLongOrNull(request.getParameter("id"));
        if (id == null) {
            String itemsJson = gson.toJson(ItemsStorage.getItems());
            response = new Response(HttpStatus.OK, itemsJson, ContentType.APPLICATION_JSON).build();
        } else {
            Item item = ItemsStorage.getItem(id)
                    .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Item not found with id: " + id));
            
            String itemJson = gson.toJson(item);
            response = new Response(HttpStatus.OK, itemJson, ContentType.APPLICATION_JSON).build();
        }

        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}

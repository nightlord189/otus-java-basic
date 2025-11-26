package org.aburavov.otus.java.basic.http.server.application;

import com.google.gson.Gson;
import org.aburavov.otus.java.basic.http.server.HttpRequest;
import org.aburavov.otus.java.basic.http.server.Response;
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
            response = new Response(200, itemsJson, Response.CONTENT_TYPE_APPLICATION_JSON).build();
        } else {
            Item item = ItemsStorage.getItem(id);
            if (item == null) {
                response = new Response(404, null, Response.CONTENT_TYPE_TEXT_HTML).build();
            } else {
                String itemJson = gson.toJson(item);
                response = new Response(200, itemJson, Response.CONTENT_TYPE_APPLICATION_JSON).build();
            }
        }

        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}

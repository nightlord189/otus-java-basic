package org.aburavov.otus.java.basic.http.server.application;

import com.google.gson.Gson;
import org.aburavov.otus.java.basic.http.server.HttpRequest;
import org.aburavov.otus.java.basic.http.server.Response;
import org.aburavov.otus.java.basic.http.server.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DeleteItemsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        // DELETE /api/v1/items?id=10
        Long id = Util.parseLongOrNull(request.getParameter("id"));
        if (id == null) {
            String response = new Response(400, "invalid id", Response.CONTENT_TYPE_TEXT_HTML).build();
            output.write(response.getBytes(StandardCharsets.UTF_8));
            return;
        }

        ItemsStorage.deleteItem(id);

        String response = new Response(204, null, Response.CONTENT_TYPE_TEXT_HTML).build();
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}

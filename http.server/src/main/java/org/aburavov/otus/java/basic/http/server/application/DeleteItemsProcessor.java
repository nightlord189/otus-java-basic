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

public class DeleteItemsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        // DELETE /api/v1/items?id=10
        Long id = Util.parseLongOrNull(request.getParameter("id"));
        if (id == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "invalid id");
        }

        ItemsStorage.deleteItem(id);

        String response = new Response(HttpStatus.NO_CONTENT, null, ContentType.TEXT_HTML).build();
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}

package org.aburavov.otus.java.basic.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class Response {
    private final static Logger logger = LogManager.getLogger(HttpServer.class.getName());

    public static final String CONTENT_TYPE_TEXT_HTML = "text/html";
    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    public static final Map<Integer, String> STATUS_DESCRIPTIONS = Map.of(
            200, "OK",
            201, "Created",
            204, "No Content",
            400, "Bad Request",
            404, "Not Found",
            500, "Internal Server Error"
    );

    private final int statusCode;
    private String body = "";
    private final Map<String, String> headers;

    public Response(int statusCode, String body, Map<String, String> headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
    }

    public Response(int statusCode, String body, String contentType) {
        this.statusCode = statusCode;
        this.body = body;
        headers = Map.of(
                HEADER_CONTENT_TYPE, contentType
        );
    }

    public Response(int statusCode) {
        this.statusCode = statusCode;
        this.headers = Map.of(
                HEADER_CONTENT_TYPE, CONTENT_TYPE_TEXT_HTML
        );
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 " + statusCode + " " + STATUS_DESCRIPTIONS.get(statusCode) + "\r\n");
        for (Map.Entry entry : headers.entrySet()) {
            sb.append(entry.getKey() + ": " + entry.getValue() + "\r\n");
        }
        if (body == null) {
            body = "";
        }
        sb.append("\r\n" + body);
        String result = sb.toString();
        logger.debug("RESPONSE:\n{}", result);
        return result;
    }
}

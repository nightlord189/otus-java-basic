package org.aburavov.otus.java.basic.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class Response {
    private final static Logger logger = LogManager.getLogger(Response.class.getName());

    private final HttpStatus status;
    private String body = "";
    private final Map<HeaderType, String> headers;

    public Response(HttpStatus status, String body, Map<HeaderType, String> headers) {
        this.status = status;
        this.body = body;
        this.headers = headers;
    }

    public Response(HttpStatus status, String body, ContentType contentType) {
        this.status = status;
        this.body = body;
        headers = Map.of(
                HeaderType.CONTENT_TYPE, contentType.getValue()
        );
    }

    public Response(HttpStatus status) {
        this.status = status;
        this.headers = Map.of(
                HeaderType.CONTENT_TYPE, ContentType.TEXT_HTML.getValue()
        );
    }

    public Response addHeader(HeaderType key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 " + status.getCode() + " " + status.getDescription() + "\r\n");
        for (Map.Entry entry : headers.entrySet()) {
            sb.append(entry.getKey().toString() + ": " + entry.getValue() + "\r\n");
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

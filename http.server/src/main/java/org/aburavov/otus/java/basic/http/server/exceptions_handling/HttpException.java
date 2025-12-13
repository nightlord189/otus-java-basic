package org.aburavov.otus.java.basic.http.server.exceptions_handling;

import org.aburavov.otus.java.basic.http.server.HttpStatus;

public class HttpException extends RuntimeException {
    private final HttpStatus status;

    public HttpException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

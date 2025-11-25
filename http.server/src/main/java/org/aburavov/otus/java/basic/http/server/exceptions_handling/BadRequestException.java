package org.aburavov.otus.java.basic.http.server.exceptions_handling;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

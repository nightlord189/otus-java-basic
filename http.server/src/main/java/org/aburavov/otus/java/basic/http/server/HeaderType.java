package org.aburavov.otus.java.basic.http.server;

public enum HeaderType {
    CONTENT_TYPE("Content-Type"),
    CONTENT_LENGTH("Content-Length");

    private final String value;

    HeaderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

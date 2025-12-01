package org.aburavov.otus.java.basic.http.server;

public enum ContentType {
    TEXT_HTML("text/html"),
    APPLICATION_JSON("application/json");

    private final String value;

    ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
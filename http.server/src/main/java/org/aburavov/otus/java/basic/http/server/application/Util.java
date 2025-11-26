package org.aburavov.otus.java.basic.http.server.application;

import org.aburavov.otus.java.basic.http.server.Response;

import java.nio.charset.StandardCharsets;

public class Util {
    public static Long parseLongOrNull(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

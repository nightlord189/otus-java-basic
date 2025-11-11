package org.aburavov.otus.java.basic.hw.hw13.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {
    public static final char PairDelimiter = ';';
    public static final char KeyValueDelimiter = ':';

    public static Map<Parameter, String> decode(String input) throws ProtocolException {
        String[] pairs = input.split(String.valueOf(PairDelimiter));

        Map<Parameter, String> result = new HashMap<>();

        for (String pair : pairs) {
            String[] splitted = pair.split(String.valueOf(KeyValueDelimiter));
            if (splitted.length != 2) {
                throw new ProtocolException("Incorrect key-value entry length: " + pair + ": " + splitted.length);
            }
            Parameter key = Parameter.valueOf(splitted[0]);
            result.put(key, splitted[1]);
        }

        return result;
    }

    public static String encode(Map<Parameter, String> map) {
        List<String> pairs = new ArrayList<>();

        for (Map.Entry<Parameter, String> entry : map.entrySet()) {
            pairs.add(entry.getKey() + String.valueOf(KeyValueDelimiter) + entry.getValue());
        }

        return String.join(String.valueOf(PairDelimiter), pairs);
    }
}

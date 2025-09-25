package org.aburavov.otus.java.basic.hw10;

import java.util.*;

public class PhoneBook {
    private final Map<String, Set<String>> database = new HashMap<>();
    private final Set<String> phones = new HashSet<>();

    public void add(String name, String phone) {
        Set<String> phonesByName = database.get(name);
        if (phonesByName == null) {
            phonesByName = new HashSet<>();
        }
        phonesByName.add(phone);
        database.put(name, phonesByName);
        phones.add(phone);
    }

    public Set<String> find(String name) {
        return database.getOrDefault(name, Collections.emptySet());
    }

    public boolean containsPhoneNumber(String phone) {
        return phones.contains(phone);
    }
}

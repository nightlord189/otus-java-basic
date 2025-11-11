package hw10;

import java.util.*;
import java.util.function.Function;

public class PhoneBook {
    private final Map<String, Set<String>> database = new HashMap<>();
    private final Set<String> phones = new HashSet<>();

    public void add(String name, String phone) {
        database.computeIfAbsent(name, key -> new HashSet<>());
        Set<String> phonesByName = database.get(name);
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

package org.example.PhoneBook;

import java.util.*;

public class PhoneBook {
    private Map<String, List<String>> directory;
    public PhoneBook() {
        this.directory = new HashMap<>();
    }

    public void add(String surname, String phoneNumber) {
        if (directory.containsKey(surname)) {
            List<String> phones = directory.get(surname);
            if (!phones.contains(phoneNumber)) {
                phones.add(phoneNumber);
            }
        } else {
            List<String> phones = new ArrayList<>();
            phones.add(phoneNumber);
            directory.put(surname, phones);
        }
    }

    public List<String> get(String surname) {
        return directory.getOrDefault(surname, new ArrayList<>());
    }
}

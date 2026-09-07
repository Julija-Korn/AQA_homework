package org.example.PhoneBook;

import java.util.*;

public class MainPhoneBook {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Ваниева", "+7-961-456-78-90");
        phoneBook.add("Корниенко", "+7-923-456-78-91");
        phoneBook.add("Загоруйко", "+7-918-567-89-01");
        phoneBook.add("Ваниева", "+7-922-678-90-12");
        phoneBook.add("Загоруйко", "+7-906-456-78-92");

        System.out.println("Телефоны Ваниевой: " + phoneBook.get("Ваниева"));
        System.out.println("Телефоны Корниенко: " + phoneBook.get("Корниенко"));
        System.out.println("Телефоны Загоруйко: " + phoneBook.get("Загоруйко"));

        System.out.println("\nВсе телефоны Иванова:");
        List<String> ivanovPhones = phoneBook.get("Иванов");
        for (String phone : ivanovPhones) {
            System.out.println(phone);
        }
    }
}

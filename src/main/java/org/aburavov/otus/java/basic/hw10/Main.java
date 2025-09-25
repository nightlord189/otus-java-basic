package org.aburavov.otus.java.basic.hw10;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is HW10");

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("John", "+12345");
        phoneBook.add("John", "+8134590");
        phoneBook.add("Ivan", "+79678");
        phoneBook.add("Ivan", "+79678");
        phoneBook.add("Khan", "+827451");

        System.out.println("find by name");
        System.out.println("John: " + phoneBook.find("John"));
        System.out.println("Ivan: " + phoneBook.find("Ivan"));
        System.out.println("Khan: " + phoneBook.find("Khan"));
        System.out.println("Donald: " + phoneBook.find("Donald"));

        System.out.println("containsPhoneNumber");
        System.out.println("+12345: " + phoneBook.containsPhoneNumber("+12345"));
        System.out.println("+10000: " + phoneBook.containsPhoneNumber("+10000"));
    }
}

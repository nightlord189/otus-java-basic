package org.aburavov.otus.java.basic.hw.hw04;

public class User {
    private final String lastname;
    private final String firstname;
    private final String patronymic;
    private final int birthYear;
    private final String email;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getEmail() {
        return email;
    }

    public User(String lastname, String firstname, String patronymic, int birthYear, String email) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.birthYear = birthYear;
        this.email = email;
    }

    public void printInfo() {
        System.out.println("ФИО: " + lastname + " " + firstname + " " + patronymic);
        System.out.println("Год рождения: " + birthYear);
        System.out.println("e-mail: " + email);
    }
}

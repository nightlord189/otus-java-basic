package hw04;

import java.time.Year;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is HW04");

        User[] users = new User[]{
                new User("Иванов", "Иван", "Иванович", 1980, "ivan@gmail.com"),
                new User("Петров", "Петр", "Петрович", 2000, "petr@gmail.com"),
                new User("Кожахметов", "Серик", "Султанулы", 1985, "kserik@gmail.com"),
                new User("Coniglio", "Frederic", "", 1984, "cfredo@yahoo.com"),
                new User("Хван", "Алексей", "Леонидович", 2001, "ahvan@gmail.com"),
                new User("Сидорова", "Мария", "Александровна", 1995, "msidorova@mail.ru"),
                new User("Kim", "Daniel", "", 1992, "daniel.kim@gmail.com"),
                new User("Абдрахманов", "Бекзат", "Кайратулы", 1979, "bekzat88@inbox.kz"),
                new User("Müller", "Anna", "", 1997, "anna.mueller@gmx.de"),
                new User("Смирнов", "Дмитрий", "Владимирович", 1976, "dsmirnov76@yandex.ru"),
        };

        int currentYear = Year.now().getValue();

        System.out.println("Users older than 40 years");

        for (int i = 0; i < users.length; i++) {
            if (currentYear - users[i].getBirthYear() >= 40) {
                users[i].printInfo();
            }
        }

        Box box = new Box(10, 15, 5, "green");
        box.printInfo();
        box.setColor("black");
        box.open();
        box.putContent("toy");
        box.close();
        box.printInfo();
        box.open();
        box.throwOutContent();
        box.setColor("yellow");
        box.printInfo();
        box.close();
    }
}

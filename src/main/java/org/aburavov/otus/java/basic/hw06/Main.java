package org.aburavov.otus.java.basic.hw06;

public class Main {
    public static void main(String[] args) {
        Plate plate = new Plate(10);

        Cat[] cats = new Cat[]{
                new Cat("Abaddon", 3),
                new Cat("Konrad", 3),
                new Cat("Angron", 5)
        };

        plate.info();
        for (int i = 0; i < cats.length; i++) {
            System.out.println(cats[i]);
        }

        System.out.println("Feeding...");

        for (int i = 0; i < cats.length; i++) {
            cats[i].eat(plate);
        }

        plate.info();
        for (int i = 0; i < cats.length; i++) {
            System.out.println(cats[i]);
        }
    }
}

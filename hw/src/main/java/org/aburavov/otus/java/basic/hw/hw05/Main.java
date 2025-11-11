package org.aburavov.otus.java.basic.hw.hw05;

import org.aburavov.otus.java.basic.hw.hw05.animals.Animal;
import org.aburavov.otus.java.basic.hw.hw05.animals.Cat;
import org.aburavov.otus.java.basic.hw.hw05.animals.Dog;
import org.aburavov.otus.java.basic.hw.hw05.animals.Horse;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start");

        Animal[] animals = new Animal[]{
                new Cat("Murray", 1, 10),
                new Dog("Tiran", 2, 1, 30),
                new Horse("Meteor", 4, 2, 40)
        };

        for (int i = 0; i < animals.length; i++) {
            animals[i].info();

            int runTime = animals[i].run(10);
            System.out.println("ran in " + runTime + " seconds");
            animals[i].info();

            int swimTime = animals[i].swim(10);
            System.out.println("swam in " + swimTime + " seconds");
            animals[i].info();

            System.out.println();
        }
    }
}

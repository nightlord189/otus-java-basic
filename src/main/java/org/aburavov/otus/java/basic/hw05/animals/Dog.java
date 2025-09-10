package org.aburavov.otus.java.basic.hw05.animals;

public class Dog extends Animal {
    public Dog(String name, int runSpeed, int swimSpeed, int stamina) {
        super(name, runSpeed, swimSpeed, stamina);
        this.swimStaminaCost = 2;
    }
}

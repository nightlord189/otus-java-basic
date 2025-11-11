package org.aburavov.otus.java.basic.hw.hw05.animals;

public class Horse extends Animal {
    public Horse(String name, int runSpeed, int swimSpeed, int stamina) {
        super(name, runSpeed, swimSpeed, stamina);
        this.swimStaminaCost = 4;
    }
}

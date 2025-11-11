package org.aburavov.otus.java.basic.hw.hw06;

public class Cat {
    private final String name;
    private final int appetite;
    private boolean isHungry = true;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public boolean eat(Plate plate) {
        if (plate.decreaseFood(appetite)) {
            this.isHungry = false;
            System.out.println(name + " ate");
            return true;
        } else {
            System.out.println(name + " not enough food to eat :(");
            return false;
        }
    }

    public void info() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return name + ", appetite: " + appetite + ", isHungry: " + isHungry;
    }
}

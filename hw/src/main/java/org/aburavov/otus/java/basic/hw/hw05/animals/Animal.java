package org.aburavov.otus.java.basic.hw.hw05.animals;

public abstract class Animal {
    private final String name;
    private final int runSpeed;
    private final int swimSpeed;
    protected int runStaminaCost = 1;
    protected int swimStaminaCost;
    private int stamina;


    public Animal(String name, int runSpeed, int swimSpeed, int stamina) {
        this.name = name;
        this.runSpeed = runSpeed;
        this.swimSpeed = swimSpeed;
        this.stamina = stamina;
    }

    public int run(int distance) {
        System.out.println(this.getClass().getSimpleName() + " " + name + " is running a distance of " + distance);
        return move(distance, runStaminaCost, runSpeed);
    }

    public int swim(int distance) {
        System.out.println(this.getClass().getSimpleName() + " " + name + " is swimming a distance of " + distance);
        return move(distance, swimStaminaCost, swimSpeed);
    }

    protected int move(int distance, int moveStaminaCost, int speed) {
        int staminaCost = moveStaminaCost * distance;

        if (stamina - staminaCost < 0) {
            System.out.println(this.getClass().getSimpleName() + " " + name + " is tired :(");
            return -1;
        }

        stamina -= staminaCost;

        return distance / speed;
    }

    public void info() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return getName() + ", runSpeed: " + runSpeed + ", swimSpeed: " + swimSpeed + ", stamina: " + stamina;
    }

    public String getName() {
        return this.getClass().getSimpleName() + " " + name;
    }
}

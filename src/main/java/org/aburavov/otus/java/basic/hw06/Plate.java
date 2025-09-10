package org.aburavov.otus.java.basic.hw06;

public class Plate {
    private final int maxSize;
    private int currentSize;

    public Plate(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = maxSize;
    }

    public void increaseFood(int amount) {
        this.currentSize += amount;
        if (currentSize > maxSize) {
            currentSize = maxSize;
        }
    }

    public boolean decreaseFood(int amount) {
        if (this.currentSize - amount >= 0) {
            this.currentSize -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void info() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "maxSize: " + maxSize + ", currentSize: " + currentSize;
    }
}

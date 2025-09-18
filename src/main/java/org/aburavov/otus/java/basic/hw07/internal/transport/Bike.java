package org.aburavov.otus.java.basic.hw07.internal.transport;

import org.aburavov.otus.java.basic.hw07.internal.*;

public class Bike extends BaseTransport implements Movable {
    private final int staminaConsumption;
    private Human driver;

    public Bike(int staminaConsumption) {
        this.staminaConsumption = staminaConsumption;
    }

    @Override
    public void sit(Human driver) {
        this.driver = driver;
    }

    @Override
    public boolean move(Surface surface, int distance) {
        if (surface == Surface.SWAMP) {
            System.out.println(getSimpleName() + " cannot move on swamp :(");
            return false;
        }
        if (driver == null) {
            System.out.println(getSimpleName() + " cannot move without a driver :(");
        }

        int staminaCost = distance * staminaConsumption;
        if (driver.getStamina() - staminaCost < 0) {
            System.out.println(getSimpleName() + " cannot move: the driver is tired :(");
            return false;
        }
        driver.decreaseStamina(staminaCost);

        System.out.println(getSimpleName() + " moved to distance " + distance + " on surface " + surface + " with stamina cost: " + staminaCost);
        return true;
    }
}

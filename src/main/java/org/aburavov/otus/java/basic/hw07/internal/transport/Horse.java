package org.aburavov.otus.java.basic.hw07.internal.transport;

import org.aburavov.otus.java.basic.hw07.internal.Movable;
import org.aburavov.otus.java.basic.hw07.internal.Surface;

public class Horse extends BaseTransport implements Movable {
    private int stamina;
    private final int staminaConsumption;

    public int getStamina() {
        return stamina;
    }

    public Horse(int stamina, int staminaConsumption) {
        this.stamina = stamina;
        this.staminaConsumption = staminaConsumption;
    }

    @Override
    public boolean move(Surface surface, int distance) {
        if (surface == Surface.SWAMP) {
            System.out.println(getSimpleName() + " cannot move on swamp :(");
            return false;
        }
        int staminaCost = distance * staminaConsumption;
        if (stamina - staminaCost < 0) {
            System.out.println(getSimpleName() + " cannot move because is tired :(");
        }
        stamina -= staminaCost;
        System.out.println(getSimpleName() + " moved to distance " + distance + " on surface " + surface + " with stamina cost: " + staminaCost);
        return true;
    }
}
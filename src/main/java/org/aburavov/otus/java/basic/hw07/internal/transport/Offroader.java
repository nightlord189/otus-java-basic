package org.aburavov.otus.java.basic.hw07.internal.transport;

import org.aburavov.otus.java.basic.hw07.internal.Entity;
import org.aburavov.otus.java.basic.hw07.internal.IMovable;
import org.aburavov.otus.java.basic.hw07.internal.Surface;

public class Offroader extends BaseTransport implements IMovable {
    private int petrol;
    private final int petrolConsumption;

    public int getPetrol() {
        return petrol;
    }

    public Offroader(int petrol, int petrolConsumption) {
        this.petrol = petrol;
        this.petrolConsumption = petrolConsumption;
    }

    @Override
    public boolean move(Surface surface, int distance) {
        if (!canPassSurface(surface)) {
            return false;
        }
        var petrolCost = distance * petrolConsumption;
        if (petrol - petrolCost < 0) {
            System.out.println(getSimpleName() + " cannot move: not enough fuel :(");
            return false;
        }
        petrol -= petrolCost;
        System.out.println(getSimpleName() +" moved to distance " + distance + " on surface " + surface + " with petrol cost: " + petrolCost);
        return true;
    }

    protected boolean canPassSurface(Surface surface) {
        return true;
    }
}

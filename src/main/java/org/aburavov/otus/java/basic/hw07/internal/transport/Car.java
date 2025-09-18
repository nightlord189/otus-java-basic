package org.aburavov.otus.java.basic.hw07.internal.transport;

import org.aburavov.otus.java.basic.hw07.internal.Surface;

public class Car extends Offroader {
    public Car(int petrol, int petrolConsumption) {
        super(petrol, petrolConsumption);
    }

    @Override
    protected boolean canPassSurface(Surface surface) {
        if (surface != Surface.PLAIN) {
            System.out.println(getSimpleName() + " cannot move on non-plan surface :(");
            return false;
        }
        return true;
    }
}

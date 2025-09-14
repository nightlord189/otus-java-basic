package org.aburavov.otus.java.basic.hw07;

import org.aburavov.otus.java.basic.hw07.internal.Human;
import org.aburavov.otus.java.basic.hw07.internal.Surface;
import org.aburavov.otus.java.basic.hw07.internal.transport.Bike;
import org.aburavov.otus.java.basic.hw07.internal.transport.Car;
import org.aburavov.otus.java.basic.hw07.internal.transport.Horse;
import org.aburavov.otus.java.basic.hw07.internal.transport.Offroader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is HW07");

        var car = new Car(10, 1);
        var offroader = new Offroader(10, 2);
        var horse = new Horse(20, 2);
        var bike = new Bike(5);

        var human = new Human("Alex", 10, 1, null);
        human.sitOnTransport(car);

        human.move(Surface.SWAMP, 10);
        human.move(Surface.PLAIN, 10);
        human.move(Surface.PLAIN, 10);
        human.getOffFromTransport();

        human.sitOnTransport(offroader);
        human.move(Surface.SWAMP, 5);
        human.getOffFromTransport();

        human.sitOnTransport(horse);
        human.move(Surface.FOREST, 5);
        human.getOffFromTransport();

        human.sitOnTransport(bike);
        human.move(Surface.PLAIN, 1);
        human.getOffFromTransport();

        human.move(Surface.PLAIN, 1);
    }
}

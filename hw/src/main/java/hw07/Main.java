package hw07;

import hw07.internal.Human;
import hw07.internal.Surface;
import hw07.internal.transport.Bike;
import hw07.internal.transport.Car;
import hw07.internal.transport.Horse;
import hw07.internal.transport.Offroader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is HW07");

        Car car = new Car(10, 1);
        Offroader offroader = new Offroader(10, 2);
        Horse horse = new Horse(20, 2);
        Bike bike = new Bike(5);

        Human human = new Human("Alex", 10, 1, null);
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

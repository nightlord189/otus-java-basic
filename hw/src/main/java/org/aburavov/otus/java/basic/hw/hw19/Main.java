package org.aburavov.otus.java.basic.hw.hw19;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is HW19");

        Box<Apple> boxOfApples = new Box<>();
        boxOfApples.add(new Apple());

        Box<Orange> boxOfOranges = new Box<>();
        for (int i = 0; i < 3; i++) {
            boxOfOranges.add(new Orange());
        }

        System.out.println("Weight of box with apples: " + boxOfApples.weight());
        System.out.println("Weight of box with oranges: " + boxOfOranges.weight());

        System.out.println("Do boxes store the same weight: " + boxOfApples.compare(boxOfOranges));

        System.out.println("Moving apples to a new box");
        Box<Apple> boxOfApples2 = new Box<>();
        boxOfApples.moveToAnotherBox(boxOfApples2);

        System.out.println("Weight of box with apples: " + boxOfApples.weight());
        System.out.println("Weight of box with apples #2: " + boxOfApples2.weight());
        System.out.println("Weight of box with oranges: " + boxOfOranges.weight());

    }
}

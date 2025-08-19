package org.aburavov.otus.java.basic.hw01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is HW01");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Пожалуйста, введите число от 1 до 5");
        final int select = scanner.nextInt();

        switch (select) {
            case 1:
                greetings();
                break;
            case 2:
                checkSign(getRandomNumber(-5, 5), getRandomNumber(-5, 5), getRandomNumber(-5, 5));
                break;
            case 3:
                selectColor();
                break;
            case 4:
                compareNumbers();
                break;
            case 5:
                boolean increment;
                if (getRandomNumber(0, 1) == 1) {
                    increment = true;
                } else {
                    increment = false;
                }
                addOrSubtractAndPrint(getRandomNumber(1, 5), getRandomNumber(1, 5), increment);
                break;
            default:
                System.out.println("Такой метод я не могу вызвать :(");
        }
    }

    static void greetings() {
        System.out.println("Hello\nWorld\nfrom\nJava");
    }

    static void checkSign(int a, int b, int c) {
        final int sum = a + b + c;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    static void selectColor() {
        final int data = getRandomNumber(0, 30);
        if (data <= 10) {
            System.out.println("Красный");
        } else if (data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    static void compareNumbers() {
        final int a = getRandomNumber(1, 5);
        final int b = getRandomNumber(1, 5);
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        int result = initValue;
        if (increment) {
            result += delta;
        } else {
            result -= delta;
        }
        System.out.println(result);
    }

    // randomNumber - returns [from; to]
    static int getRandomNumber(int from, int to) {
        return from + (int) (Math.random() * (to - from + 1));
    }
}



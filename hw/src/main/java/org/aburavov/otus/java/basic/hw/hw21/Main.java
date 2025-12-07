package org.aburavov.otus.java.basic.hw.hw21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int SINGLE_LETTER_COUNT = 10;

    private static final Object monitor = new Object();

    private static String lastPrintedLetter = "";

    public static void main(String[] args) {
        System.out.println("Hello, this is HW21");

        ExecutorService serv = Executors.newFixedThreadPool(3);

        serv.execute(() -> {
            print("A", "C", SINGLE_LETTER_COUNT);
        });
        serv.execute(() -> {
            print("B", "A", SINGLE_LETTER_COUNT);
        });
        serv.execute(() -> {
            print("C", "B", SINGLE_LETTER_COUNT);
        });

        serv.shutdown();
        serv.close();

        System.out.println("\nDONE");
    }

    private static void print(String letter, String previousLetter, int count) {
        try {
            for (int i = 0; i < count; i++) {
                synchronized (monitor) {
                    while (!lastPrintedLetter.equals(previousLetter) && !lastPrintedLetter.isEmpty()) {
                        monitor.wait();
                    }
                    System.out.print(letter);
                    lastPrintedLetter = letter;
                    monitor.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + e);
        }
    }
}

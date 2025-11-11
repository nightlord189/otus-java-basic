package hw14;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int ARRAY_LENGTH = 100_000_000;
    private static final int THREADS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("Hello, this is HW14");

        System.out.println("Single thread");
        runAndMeasure(Main::implementationSingleThread);

        System.out.println("Multi thread");
        runAndMeasure(Main::implementationMultiThread);
    }

    private static void runAndMeasure(Runnable action) {
        Instant start = Instant.now();
        action.run();
        Duration duration = Duration.between(start, Instant.now());
        System.out.println("Implementation finished in " + duration.toString());
    }

    private static void implementationSingleThread() {
        Double[] array = new Double[ARRAY_LENGTH];
        fillArrayInIndexes(array, 0, array.length);
    }

    private static void implementationMultiThread() {
        Double[] array = new Double[ARRAY_LENGTH];

        int batchSize = array.length / THREADS_COUNT;
        System.out.println("Batch size: " + batchSize);

        List<Thread> threads = new ArrayList<>();

        for (int start = 0; start < array.length; start += batchSize) {
            int currentStart = start;
            Thread th = new Thread(() -> {
                fillArrayInIndexes(array, currentStart, currentStart + batchSize);
            });
            th.start();
            threads.add(th);
        }

        try {
            for (Thread th : threads) {
                th.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void fillArrayInIndexes(Double[] array, int start, int end) {
        System.out.println(Thread.currentThread().getName() + ": running in indexes [" + start + " : " + end + ")");
        for (int i = start; i < end; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
        System.out.println(Thread.currentThread().getName() + ": finished in indexes [" + start + " : " + end + ")");
    }
}

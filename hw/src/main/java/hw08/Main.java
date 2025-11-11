package hw08;

import hw08.exceptions.AppArrayDataException;
import hw08.exceptions.AppArraySizeException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is HW08");

        System.out.println("Demonstrating AppArraySizeException");
        demo(new String[][]{{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3"}});


        System.out.println("Demonstrating AppArrayDataException");
        demo(new String[][]{{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"Hello", "2", "3", "4"}});

        System.out.println("Demonstrating correct behaviour");
        demo(new String[][]{{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}});
    }

    private static void demo(String[][] array) {
        printArray(array);
        try {
            int sum = getSum(array);
            System.out.println("Sum: " + sum);
        } catch (AppArraySizeException | AppArrayDataException e) {
            System.out.println(e);
        }
    }

    public static int getSum(String[][] array) throws AppArraySizeException, AppArrayDataException {
        if (!isSizeCorrect(array, 4, 4)) {
            throw new AppArraySizeException();
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    int parsed = Integer.parseInt(array[i][j]);
                    sum += parsed;
                } catch (NumberFormatException e) {
                    throw new AppArrayDataException("row " + i + ", column " + j + ": " + array[i][j]);
                }
            }
        }
        return sum;
    }

    private static boolean isSizeCorrect(String[][] array, int width, int height) {
        if (array.length != width) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != height) {
                return false;
            }
        }
        return true;
    }

    private static void printArray(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}

package hw03;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is HW03");

        int[][] arr1 = {{1, 2, 3}, {-4, -5, -6}, {-7, -8, 9}};
        System.out.println("sumOfPositiveElements: " + Arrays.deepToString(arr1) + " = " + sumOfPositiveElements(arr1));

        printSquare(5);

        int[][] arr2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        zeroOutDiagonals(arr2);
        System.out.println("array after zeroOut: ");
        printArray(arr2);

        System.out.println("findMax: " + Arrays.deepToString(arr2) + " = " + findMax(arr2));

        int column1 = 1;
        System.out.println("getSumOfColumn: " + Arrays.deepToString(arr2) + ", column " + column1 + " = " + getSumOfColumn(arr2, column1));

        int[][] arr3 = {{1, 2}, {4, 5}, {7, 8}};
        int column2 = 2;
        System.out.println("getSumOfColumn: " + Arrays.deepToString(arr3) + ", column " + column2 + " = " + getSumOfColumn(arr3, column2));
    }

    static int sumOfPositiveElements(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > 0) {
                    sum += arr[i][j];
                }
            }
        }
        return sum;
    }

    static void printSquare(int size) {
        System.out.println("printSquare: size " + size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("* ");
            }
            System.out.print('\n');
        }
    }

    static void zeroOutDiagonals(int[][] square) {
        // primary diagonal
        int i = 0;
        int j = 0;

        while (i < square.length && j <= square[0].length) {
            square[i][j] = 0;
            i++;
            j++;
        }

        // secondary diagonal
        i = 0;
        j = square[0].length - 1;

        while (i < square.length && j >= 0) {
            square[i][j] = 0;
            i++;
            j--;
        }
    }

    static int findMax(int[][] arr) {
        int max = arr[0][0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
            }
        }
        return max;
    }

    static int getSumOfColumn(int[][] arr, int column) {
        if (column >= arr[0].length) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i][column];
        }
        return sum;
    }

    static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}

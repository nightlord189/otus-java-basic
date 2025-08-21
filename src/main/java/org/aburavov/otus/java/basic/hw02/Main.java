package org.aburavov.otus.java.basic.hw02;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start");

        printStringMultiple(getRandomNumber(3, 5), "Hello!");

        sumMoreThanFive(new int[]{1, 6, 9, 2});

        fillArrayByNumber(getRandomNumber(1, 5), new int[]{1, 2, 3, 4, 5});

        increaseArrayByNumber(getRandomNumber(1, 5), new int[]{1, 2, 3, 4, 5});

        whichHalfIsBiggest(new int[]{1, 2, 3, 4, 5});

        sumArrays(new int[]{1, 2, 3}, new int[]{2, 2}, new int[]{1, 1, 1, 1, 1});

        arrayHasPointWhereLeftAndRightAreEqual(new int[]{1, 1, 1, 1, 1, 5});
        arrayHasPointWhereLeftAndRightAreEqual(new int[]{5, 3, 4, -2});
        arrayHasPointWhereLeftAndRightAreEqual(new int[]{7, 2, 2, 2});
        arrayHasPointWhereLeftAndRightAreEqual(new int[]{9, 4});

        isArraySorted(new int[]{1, 2, 3, 4, 5}, true);
        isArraySorted(new int[]{1, 2, 1}, true);
        isArraySorted(new int[]{1}, true);
        isArraySorted(new int[]{5, 4, 3}, false);
        isArraySorted(new int[]{5, 4, 8}, false);

        reverseArray(new int[]{1, 2, 3, 4});
        reverseArray(new int[]{1, 2, 3});
        reverseArray(new int[]{1});
    }

    static void printStringMultiple(int number, String str) {
        System.out.println("printStringMultiple: " + number + " " + str);
        for (int i = 0; i < number; i++) {
            System.out.println(str);
        }
    }

    static void sumMoreThanFive(int[] arr) {
        System.out.println("sumMoreThanFive: " + Arrays.toString(arr));
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 5) {
                sum += arr[i];
            }
        }
        System.out.println(sum);
    }

    static void fillArrayByNumber(int number, int[] arr) {
        System.out.println("fillArrayByNumber: " + number + " " + Arrays.toString(arr));
        System.out.println("Before: " + Arrays.toString(arr));
        for (int i = 0; i < number; i++) {
            arr[i] = number;
        }
        System.out.println("After: " + Arrays.toString(arr));
    }

    static void increaseArrayByNumber(int number, int[] arr) {
        System.out.println("increaseArrayByNumber: " + number + " " + Arrays.toString(arr));
        System.out.println("Before: " + Arrays.toString(arr));
        for (int i = 0; i < number; i++) {
            arr[i] += number;
        }
        System.out.println("After: " + Arrays.toString(arr));
    }

    static void whichHalfIsBiggest(int[] arr) {
        System.out.println("whichHalfIsBiggest: " + Arrays.toString(arr));
        int sumLeft = 0;
        int sumRight = 0;

        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            sumLeft += arr[i];
            sumRight += arr[j];

            i++;
            j--;
        }

        System.out.println("Sum left: " + sumLeft + ", sum right: " + sumRight);
        if (sumLeft > sumRight) {
            System.out.println("Сумма элементов левой половины массива больше");
        } else if (sumLeft < sumRight) {
            System.out.println("Сумма элементов правой половины массива больше");
        } else {
            System.out.println("Суммы элементов обеих половин массива равны");
        }
    }

    static void sumArrays(int[]... arrays) {
        System.out.println("sumArrays");

        int maxLength = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > maxLength) {
                maxLength = arrays[i].length;
            }
        }

        int[] result = new int[maxLength];
        for (int i = 0; i < arrays.length; i++) {
            for (int j=0; j < arrays[i].length; j++) {
                result[j] += arrays[i][j];
            }
        }

        System.out.println(Arrays.toString(result));
    }

    static void arrayHasPointWhereLeftAndRightAreEqual(int[] arr) {
        System.out.println("arrayHasPointWhereLeftAndRightAreEqual: " + Arrays.toString(arr));
        int rightSum = sumArray(arr);
        int leftSum = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            leftSum += arr[i];
            rightSum -= arr[i];
            if (leftSum == rightSum) {
                System.out.println("Point found: after index " + i + ", left sum: " + leftSum + ", right sum: " + rightSum);
                break;
            }
        }
    }

    static void isArraySorted(int[] arr, boolean ascending) {
        System.out.println("isArraySorted: " + Arrays.toString(arr) + ", ascending: " + ascending);
        boolean isSorted = true;
        if (arr.length > 1) {
            for (int i = 1; i < arr.length; i++) {
                if ((arr[i] < arr[i - 1] && ascending) || (arr[i] > arr[i - 1] && !ascending)) {
                    isSorted = false;
                    break;
                }
            }
        }
        if (isSorted) {
            System.out.println("Array is sorted");
        } else {
            System.out.println("Array is not sorted");
        }
    }

    static void reverseArray(int[] arr) {
        System.out.println("reverseArray: " + Arrays.toString(arr));
        int temp;
        for (int i = 0; i < arr.length / 2; i++) {
            int endIdx = arr.length - 1 - i;
            temp = arr[endIdx];
            arr[endIdx] = arr[i];
            arr[i] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    static int sumArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    // randomNumber - returns [from; to]
    static int getRandomNumber(int from, int to) {
        return from + (int) (Math.random() * (to - from + 1));
    }
}

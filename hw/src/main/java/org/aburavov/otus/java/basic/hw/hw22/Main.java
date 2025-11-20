package org.aburavov.otus.java.basic.hw.hw22;

public class Main {
    public static int[] filterArrayAllAfterLastOne(int[] arr) {
        int lastOneIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                lastOneIndex = i;
            }
        }

        if (lastOneIndex < 0) {
            throw new RuntimeException("Array doesn't contain ones");
        }

        int[] result = new int[arr.length - lastOneIndex - 1];
        int currentIndex = 0;

        for (int i = lastOneIndex + 1; i < arr.length; i++) {
            result[currentIndex] = arr[i];
            currentIndex++;
        }

        return result;
    }

    public static boolean containsOnlyOneAndTwo(int[] arr) {
        boolean hasOne = false;
        boolean hasTwo = false;

        for (int val : arr) {
            if (val == 1) {
                hasOne = true;
            } else if (val == 2) {
                hasTwo = true;
            } else {
                return false;
            }
        }

        return hasOne && hasTwo;
    }
}

package org.aburavov.otus.java.basic.hw.hw20;

import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is HW20");

        System.out.println("Please input filename (for example README.md)");
        Scanner sc = new Scanner(System.in);
        String filename = sc.nextLine();
        System.out.println("You entered file " + filename);
        System.out.println("Please input substring to count in file " + filename + " (for example 'Java')");
        String substring = sc.nextLine();
        System.out.println("You entered substring " + substring);

        int substringCount = calculateSubstringCountInFile(filename, substring);
        System.out.println("Substring " + filename + " appears in file " + filename + " " + substringCount + " times.");
    }

    private static int calculateSubstringCountInFile(String filename, String substring) {
        if (substring.isEmpty()) {
            return 0;
        }

        int result = 0;
        try (FileReader fr = new FileReader(filename, StandardCharsets.UTF_8)) {
            char[] targetChars = substring.toCharArray();
            int currentIdxInTargetChars = 0;

            int byteRead;
            while ((byteRead = fr.read()) != -1) {
                if (targetChars[currentIdxInTargetChars] == byteRead) {
                    currentIdxInTargetChars++;
                    if (currentIdxInTargetChars >= targetChars.length) {
                        result++;
                        currentIdxInTargetChars = 0;
                    }
                } else {
                    currentIdxInTargetChars = 0;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

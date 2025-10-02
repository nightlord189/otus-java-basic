package org.aburavov.otus.java.basic.hw12;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is HW12");

        List<String> filenames = getRootFiles();

        System.out.println("Text files in the root:");
        for (String filename : filenames) {
            System.out.println(filename);
        }

        System.out.println("Please input filename you want to work with");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();

        if (!filenames.contains(filename)) {
            System.out.println("You typed wrong filename!");
            return;
        }

        System.out.println("Reading file " + filename);

        System.out.println(readFile(filename));

        System.out.println("Type any string and it will be appended to the file " + filename + ". Type 'exit' to end.");

        writeLoop(filename, scanner);

        System.out.println("Done");
    }

    private static void writeLoop(String filename, Scanner scanner) {
        try (FileOutputStream out = new FileOutputStream(filename, true)) {
            while (true) {
                String newLine = scanner.nextLine();
                if (newLine.equals("exit")) {
                    break;
                }
                byte[] buffer = newLine.getBytes(StandardCharsets.UTF_8);
                out.write('\n');
                out.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String filename) {
        StringBuilder result = new StringBuilder();
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8)) {
            int n = in.read();
            while (n != -1) {
                result.append((char) n);
                n = in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return result.toString();
    }

    private static List<String> getRootFiles() {
        String projectRoot = System.getProperty("user.dir");

        File root = new File(projectRoot);
        String[] filenames = root.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt") || name.endsWith(".md");
            }
        });
        if (filenames == null) {
            return List.of();
        }
        return List.of(filenames);
    }
}

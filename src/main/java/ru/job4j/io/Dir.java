package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File directory = new File("c:\\projects");
        if (!directory.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", directory.getAbsoluteFile()));
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", directory.getAbsoluteFile()));
        }
        displayFileInfo(directory);
    }

    private static void displayFileInfo(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.printf("File: %s%nSize: %d bytes%n%n", file.getName(), file.length());
                } else if (file.isDirectory()) {
                    System.out.printf("Directory: %s%n%n", file.getName());
                    displayFileInfo(file);
                }
            }
        }
    }
}

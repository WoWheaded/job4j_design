package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty current = new FileProperty(Files.size(file), file.getFileName().toString());
            map.computeIfAbsent(current, k -> new ArrayList<>()).add(file);
        }
        return super.visitFile(file, attrs);
    }

    public void show() {
        for (FileProperty fileProperty : map.keySet()) {
            if (map.get(fileProperty).size() > 1) {
                System.out.println("Duplicate files with size " + fileProperty.getSize() + ":");
                for (Path path : map.get(fileProperty)) {
                    System.out.println("Directory: " + path.getParent() + " _____ Name: " + path.getFileName() + " ____ Size: " + fileProperty.getSize());
                }
                System.out.println();
            }
        }
    }
}
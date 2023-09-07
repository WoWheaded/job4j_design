package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts[parts.length - 2].equals("404")) {
                    result.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveTo(String out) {
        var data = filter();
        try (PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
            for (String line : data) {
                printWriter.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        new LogFilter("data/log.txt").saveTo("data/404.txt");
        logFilter.filter().forEach(System.out::println);

    }


}

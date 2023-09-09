package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                    .forEach(line -> {
                        String[] temp = validator(line);
                        values.put(temp[0], temp[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] validator(String line) {
        if (!line.contains("=")) {
            throw new IllegalArgumentException("Config without '=' at line: '%s'".formatted(line));
        }
        String[] temp = line.split("=", 2);
        String key = temp[0];
        String value = temp[1];
        if (key.isEmpty() || value.isEmpty()) {
            throw new IllegalArgumentException("Key or value is not configure at line: '%s' ".formatted(line));

        }
        return temp;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("data/app.properties");
        System.out.println(config);
        config.load();
    }
}

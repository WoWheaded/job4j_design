package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainMain {
    public static void main(String[] args) {
        final Worker worker = new Worker(true, 200_000, "Dmitry",
                new WorkersProject("Pet clinic", 1), new String[]{"HR", "IT"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(worker));

        final String workerJson =
                "{\"isWork\":false,"
                        + "\"salary\":300000,"
                        + "\"name\":\"Dmitry\","
                        + "\"projects\":"
                        + "{\"nameOfProject\":\"Airport\",\"id\":2},"
                        + "\"departments\":"
                        + "[\"IT\"]}";

        final Worker workerFromJson = gson.fromJson(workerJson, Worker.class);
        System.out.println(workerFromJson);
    }
}

package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pojo {
    public static void main(String[] args) {
        JSONObject jsonWorkersProject = new JSONObject("{\"nameOfProject\":\"Airport\", "
                + "}\"id\":2");
        List<String> list = new ArrayList<>();
        list.add("IT");
        list.add("HR");
        JSONArray jsonDepartments = new JSONArray(list);
        final Worker worker = new Worker(true, 200_000, "Dmitry",
                new WorkersProject("Pet clinic", 1), new String[]{"HR", "IT"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isWork", worker.isWork());
        jsonObject.put("salary", worker.getSalary());
        jsonObject.put("name", worker.getName());
        jsonObject.put("projects", jsonWorkersProject);
        jsonObject.put("departments", jsonDepartments);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(worker).toString());
    }
}

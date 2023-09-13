package ru.job4j.serialization.json;

public class WorkersProject {
    private String nameOfProject;

    private int id;

    public WorkersProject(String nameOfProject, int id) {
        this.nameOfProject = nameOfProject;
        this.id = id;
    }

    public String getNameOfProject() {
        return nameOfProject;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "WorkersProject{" + "nameOfProject='" + nameOfProject + '\'' + ", id=" + id + '}';
    }
}

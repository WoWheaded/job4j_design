package ru.job4j.serialization.json;

import java.util.Arrays;

public class Worker {
    private final boolean isWork;
    private final int salary;
    private final String name;
    private final WorkersProject projects;
    private final String[] departments;

    public Worker(boolean isWork, int salary, String name, WorkersProject projects, String[] departmens) {
        this.isWork = isWork;
        this.salary = salary;
        this.name = name;
        this.projects = projects;
        this.departments = departmens;
    }

    public boolean isWork() {
        return isWork;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public WorkersProject getProjects() {
        return projects;
    }

    public String[] getDepartments() {
        return departments;
    }

    @Override
    public String toString() {
        return "Worker{"
                + "isWork=" + isWork
                + ", salary=" + salary
                + ", name='" + name + '\''
                + ", projects=" + projects
                + ", departments=" + Arrays.toString(departments) + '}';
    }
}

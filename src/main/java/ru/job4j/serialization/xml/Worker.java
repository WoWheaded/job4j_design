package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "worker")
@XmlAccessorType(XmlAccessType.FIELD)
public class Worker {
    @XmlAttribute
    private boolean isWork;
    @XmlAttribute
    private int salary;
    @XmlAttribute
    private String name;
    private WorkersProject projects;
    @XmlElementWrapper
    @XmlElement(name = "departments")
    private String[] departments;

    public Worker() {
    }

    public Worker(boolean isWork, int salary, String name, WorkersProject projects, String[] departmens) {
        this.isWork = isWork;
        this.salary = salary;
        this.name = name;
        this.projects = projects;
        this.departments = departmens;
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

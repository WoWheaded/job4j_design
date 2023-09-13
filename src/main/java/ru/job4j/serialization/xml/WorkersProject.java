package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "project ")
public class WorkersProject {
    @XmlAttribute
    private String nameOfProject;

    @XmlAttribute
    private int id;

    public WorkersProject() {
    }

    public WorkersProject(String nameOfProject, int id) {
        this.nameOfProject = nameOfProject;
        this.id = id;
    }

    @Override
    public String toString() {
        return "WorkersProject{" + "nameOfProject='" + nameOfProject + '\'' + ", id=" + id + '}';
    }
}

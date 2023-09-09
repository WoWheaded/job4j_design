package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte brothers = 2;
        short weight = 75;
        int age = 28;
        long salary = 70000L;
        float distanceToWork = 1987984.15f;
        double height = 180.7;
        String name = "Dmitry Arinin";
        char sex = 'M';
        boolean imSmoke = false;
        LOG.debug("User info name : {}, age : {}, weight : {}, height : {}, sex : {},"
                        + " salary : {}, imSmoke : {}, distanceToWork : {}, brothers : {}",
                name, age, weight, height, sex, salary, imSmoke, distanceToWork, brothers);
    }
}
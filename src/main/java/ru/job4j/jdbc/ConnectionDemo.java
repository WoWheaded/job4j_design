package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";
    private static final String LOGIN = "db.login";
    private static final String PASSWORD = "db.pass";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("C:\\projects\\job4j_design\\src\\main\\resources\\app.properties");
        config.load();
        Class.forName(config.value(DRIVER));
        try (Connection connection = DriverManager.getConnection(
                config.value(URL),
                config.value(LOGIN),
                config.value(PASSWORD))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}

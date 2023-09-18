package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws IOException, ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            Class.forName(config.getProperty("db.driver"));
            String url = config.getProperty("db.url");
            String login = config.getProperty("db.login");
            String pass = config.getProperty("db.pass");
            connection = DriverManager.getConnection(url, login, pass);
        }
    }

    private void executeStatement(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws SQLException {
        executeStatement(String.format("CREATE TABLE IF NOT EXISTS %s();", tableName));
    }

    public void dropTable(String tableName) throws SQLException {
        executeStatement(String.format("DROP TABLE %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        executeStatement(String.format("ALTER TABLE %s ADD %s %s;", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        executeStatement(String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        executeStatement(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName));
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor(new Properties());
        tableEditor.createTable("new_table");
        System.out.println(tableEditor.getTableScheme("new_table"));
        tableEditor.addColumn("new_table", "test1", "VARCHAR(50)");
        System.out.println(tableEditor.getTableScheme("new_table"));
        tableEditor.renameColumn("new_table", "test1", "newtest1");
        System.out.println(tableEditor.getTableScheme("new_table"));
        tableEditor.dropColumn("new_table", "newtest1");
        System.out.println(tableEditor.getTableScheme("new_table"));
        tableEditor.dropTable("new_table");
    }
}

package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;

        try {
            initConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initConnection() throws Exception {
        if (connection != null) {
            return;
        }

        connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"), properties.getProperty("password"));
    }

    public void createTable(String tableName) {
        executeStatement(String.format("create table if not exists %s(id serial primary key);", tableName));
    }

    public void dropTable(String tableName) {
        executeStatement(String.format("drop table if exists %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        executeStatement(String.format("alter table %s add column if not exists %s %s;", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        executeStatement(String.format("alter table %s drop column if exists %s;", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        executeStatement(String.format("alter table %s rename %s to %s;", tableName, columnName, newColumnName));
    }

    private void executeStatement(String sqlQuery) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws
            Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
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
        Class.forName("org.postgresql.Driver");
        Config config = new Config("./data/app.properties");
        config.load();

        Properties properties = new Properties();
        properties.load(new FileReader("./data/app.properties"));

        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("testtable2");
        //tableEditor.dropTable("testtable2");
        tableEditor.addColumn("testtable2", "name", "varchar(255)");
        tableEditor.dropColumn("testtable2", "name");
        tableEditor.addColumn("testtable2", "surname", "varchar(255)");
        //tableEditor.renameColumn("testtable2", "surname", "name1");
        tableEditor.close();
    }

}

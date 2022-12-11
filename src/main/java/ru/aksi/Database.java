package ru.aksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static Database instance = new Database();
    private static Connection connection;

    public static Database getInstance() {
        return instance;
    }
    private Database() {
        initDb();
    }

    public Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) return connection;
        return connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/currency.db");
    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Database - closeConnection() error: " + e);
        }
    }

    public void initDb() {
        String query = "CREATE TABLE IF NOT EXISTS 'currency_exchange' (" +
                "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
                "'value' REAL," +
                "'nominal' INTEGER," +
                "'currency_name' VARCHAR(100)," +
                "'currency_code' VARCHAR(3)," +
                "'date' DATE);";
        Connection connection;
        try {
            connection = this.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            statement.close();
            this.closeConnection();
        } catch (SQLException e) {
            System.err.printf("Database - initDb() error: " + e);
        }
    }
}

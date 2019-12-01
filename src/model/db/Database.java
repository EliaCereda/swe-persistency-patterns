package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String databaseURL = "jdbc:sqlite:database.sqlite";

    private static final String createUsersStatement =
        "CREATE TABLE IF NOT EXISTS users (" +
            "username TEXT PRIMARY KEY," +
            "password TEXT," +
            "name TEXT," +
            "best_friend TEXT REFERENCES users(username) ON DELETE SET NULL" +
        ");";
    private static final String createAddressesStatement =
        "CREATE TABLE IF NOT EXISTS addresses (" +
            "username TEXT PRIMARY KEY REFERENCES users(username) ON DELETE CASCADE," +
            "street_address TEXT" +
        ");";

    private static boolean driverLoaded = false;

    // Singleton

    private static Database sharedInstance = null;

    private Database() {
        try {
            initialiseDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Database();
        }

        return sharedInstance;
    }

    // Database Setup

    private void initialiseDatabase() throws SQLException, ClassNotFoundException {
        if (!driverLoaded) {
            Class.forName("org.sqlite.JDBC");
            driverLoaded = true;
        }

        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            Statement stmt = connection.createStatement();
            stmt.execute(createUsersStatement);
            stmt.execute(createAddressesStatement);
        }
    }
}
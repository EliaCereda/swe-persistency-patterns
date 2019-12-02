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

    private static final String insertUsersStatement =
        "INSERT OR IGNORE INTO users (username, password, name, best_friend) VALUES " +
            "('elia', 'password', 'Elia Cereda', NULL), " +
            "('mickey', 'password', 'Mickey Mouse', 'goofy'), " +
            "('pluto', 'password', 'Pluto', 'mickey'), " +
            "('goofy', 'password', 'Goofy', 'mickey');";

    private static boolean driverLoaded = false;

    // Singleton

    private static Database sharedInstance = null;

    private Database() {
        try {
            initialiseDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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

        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.execute(createUsersStatement);
            stmt.execute(createAddressesStatement);
            stmt.execute(insertUsersStatement);
        }
    }

    // Connection Management

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseURL);
    }
}

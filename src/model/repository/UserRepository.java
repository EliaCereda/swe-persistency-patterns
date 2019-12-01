package model.repository;

import model.Address;
import model.User;
import model.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private Database db;

    public UserRepository(Database db) {
        this.db = db;
    }

    // Data Mapping

    private User loadUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setBestFriend(rs.getString("best_friend"));

        return user;
    }

    // Mutators

    private static final String insertStatement =
        "INSERT INTO users (username, password, name, best_friend) VALUES (?, ?, ?, ?);";

    public void insert(User user) {
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insertStatement)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getBestFriend());
            // FIXME: add address

            stmt.executeUpdate();
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }
    }

    private static final String updateStatement =
        "UPDATE users " +
            "SET password=?, name=?, best_friend = ? " +
            "WHERE username = ?";

    public void update(User user) {
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(updateStatement)) {

            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getBestFriend());
            stmt.setString(4, user.getUsername());
            // FIXME: add address

            stmt.executeUpdate();
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }
    }

    private static final String deleteStatement =
        "DELETE FROM users WHERE username = ?";

    public void deleteByUsername(String username) {
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(deleteStatement)) {

            stmt.setString(1, username);

            stmt.executeUpdate();
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }
    }

    // Queries

    private static final String findAllQuery =
        "SELECT * FROM users";

    public List<User> findAll() {
        return findAllByQuery(findAllQuery, null);
    }

    private static final String findByUsernameQuery =
        "SELECT * FROM users WHERE username = ?";

    public User findByUsername(String username) {
        User user = null;

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(findByUsernameQuery)) {

            stmt.setString(1, username);

            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                user = loadUser(results);
            }

            assert !results.next();
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }

        return user;
    }

    private static final String findAllByNameQuery =
        "SELECT * FROM users WHERE name LIKE ?";

    public List<User> findAllByName(String name) {
        return findAllByQuery(findAllByNameQuery, name);
    }

    private static final String findAllByStreetAddressQuery =
        "SELECT users.* " +
            "FROM users JOIN addresses ON (users.username = addresses.username) " +
            "WHERE street_address LIKE ?";

    public List<User> findAllByStreetAddress(String streetAddress) {
        return findAllByQuery(findAllByStreetAddressQuery, streetAddress);
    }

    private static final String findAllByBestFriendQuery =
        "SELECT users.* " +
            "FROM users JOIN users AS bf ON (users.best_friend = bf.username) " +
            "WHERE bf.name LIKE ?";

    public List<User> findAllByBestFriend(String bestFriendName) {
        return findAllByQuery(findAllByBestFriendQuery, bestFriendName);
    }

    private List<User> findAllByQuery(String query, Object queryParam) {
        List<User> users = new ArrayList<>();

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            if (queryParam != null) {
                stmt.setObject(1, queryParam);
            }

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                User user = loadUser(results);
                users.add(user);
            }
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }

        return users;
    }
}

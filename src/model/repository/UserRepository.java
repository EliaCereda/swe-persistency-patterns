package model.repository;

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

    private User load(ResultSet rs) throws SQLException {
        User user = new User();

        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));

        return user;
    }

    // Mutators

    public void insert(User user) {

    }

    private static final String updateQuery =
        "UPDATE users " +
                "SET password=?, name=? " +
                "WHERE username = ?";

    public void update(User user) {
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(updateQuery)) {

            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }
    }

    public void deleteByUsername(String username) {

    }

    // Queries

    private static final String findAllQuery =
        "SELECT * FROM users";

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(findAllQuery)) {

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                User user = load(results);
                users.add(user);
            }
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }

        return users;
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
                user = load(results);
            }

            assert !results.next();
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }

        return user;
    }

    private static final String findAllByNameQuery =
        "SELECT * FROM users WHERE name = ?";

    public List<User> findAllByName(String name) {
        List<User> users = new ArrayList<>();

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(findAllByNameQuery)) {

            stmt.setString(1, name);

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                User user = load(results);
                users.add(user);
            }
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }

        return users;
    }

    public List<User> findAllByStreetAddress(String streetAddress) {
        throw new UnsupportedOperationException();
    }

    public List<User> findAllByBestFriend(/* FIXME */) {
        throw new UnsupportedOperationException();
    }

}

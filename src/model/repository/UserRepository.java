package model.repository;

import model.User;
import model.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository extends Repository<User> {

    public UserRepository(Database db) {
        super(db);
    }

    // Data Mapping

    @Override
    protected User load(ResultSet rs) throws SQLException {
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

    public List<User> findAll() {
        return findAllByQuery("SELECT * FROM users");
    }

    public User findByUsername(String username) {
        return findOneByQuery("SELECT * FROM users WHERE username = ?", username);
    }

    public List<User> findAllByName(String name) {
        return findAllByQuery("SELECT * FROM users WHERE name LIKE ?", name);
    }

    public List<User> findAllByStreetAddress(String streetAddress) {
        return findAllByQuery("SELECT users.* " +
            "FROM users JOIN addresses ON (users.username = addresses.username) " +
            "WHERE street_address LIKE ?", streetAddress);
    }

    public List<User> findAllByBestFriend(String bestFriendName) {
        return findAllByQuery("SELECT users.* " +
            "FROM users JOIN users AS bf ON (users.best_friend = bf.username) " +
            "WHERE bf.name LIKE ?", bestFriendName);
    }
}

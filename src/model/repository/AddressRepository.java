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

public class AddressRepository {
    private Database db;

    public AddressRepository(Database db) {
        this.db = db;
    }

    // Data Mapping

    private Address loadAddress(ResultSet rs) throws SQLException {
        Address address = new Address();

        address.setUsername(rs.getString("username"));
        address.setStreetAddress(rs.getString("street_address"));

        return address;
    }

    // Mutators

    private static final String insertStatement =
        "INSERT INTO addresses (username, street_address) VALUES (?, ?);";

    public void insert(Address address) {
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insertStatement)) {

            stmt.setString(1, address.getUsername());
            stmt.setString(2, address.getStreetAddress());

            stmt.executeUpdate();
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }
    }

    private static final String updateStatement =
        "UPDATE addresses " +
            "SET street_address = ? " +
            "WHERE username = ?";

    public void update(Address address) {
        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(updateStatement)) {

            stmt.setString(1, address.getStreetAddress());
            stmt.setString(2, address.getUsername());
            // FIXME: add address

            stmt.executeUpdate();
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }
    }

    // Queries

    private static final String findAllQuery =
        "SELECT * FROM users";

    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<>();

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(findAllQuery)) {

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                Address address = loadAddress(results);
                addresses.add(address);
            }
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }

        return addresses;
    }

    private static final String findByUsernameQuery =
            "SELECT * FROM addresses WHERE username = ?";

    public Address findByUsername(String username) {
        Address address = null;

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(findByUsernameQuery)) {

            stmt.setString(1, username);

            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                address = loadAddress(results);
            }

            assert !results.next();
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }

        return address;
    }

    public Address findByUser(User user) {
        return findByUsername(user.getUsername());
    }
}

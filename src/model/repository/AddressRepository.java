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

public class AddressRepository extends Repository<Address> {

    public AddressRepository(Database db) {
        super(db);
    }

    // Data Mapping

    protected Address load(ResultSet rs) throws SQLException {
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

            stmt.executeUpdate();
        } catch (SQLException e) {
            // FIXME
            e.printStackTrace();
        }
    }

    // Queries

    public List<Address> findAll() {
        return findAllByQuery("SELECT * FROM addresses");
    }

    public Address findByUsername(String username) {
        return findOneByQuery("SELECT * FROM addresses WHERE username = ?", username);
    }

    public Address findByUser(User user) {
        return findByUsername(user.getUsername());
    }
}

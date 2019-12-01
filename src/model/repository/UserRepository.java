package model.repository;

import model.User;
import model.db.Database;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private Database db;

    public UserRepository(Database db) {
        this.db = db;
    }

    // Data Mapping

    private User load(ResultSet rs) {
        throw new UnsupportedOperationException();
    }

    // Mutators

    public void insert(User user) {

    }

    public void update(User user) {

    }

    public void deleteByUsername(String username) {

    }

    // Queries

    public List<User> findAll() {
        User u1 = new User();
        u1.setUsername("elia");
        u1.setPassword("password");
        u1.setName("Elia Cereda");

        return List.of(u1);
    }

    public User findByUsername(String username) {
        User u1 = new User();
        u1.setUsername("elia");
        u1.setPassword("password");
        u1.setName("Elia Cereda");

        return u1;
    }

    public List<User> findAllByName(String name) {
        throw new UnsupportedOperationException();
    }

    public List<User> findAllByStreetAddress(String streetAddress) {
        throw new UnsupportedOperationException();
    }

    public List<User> findAllByBestFriend(/* FIXME */) {
        throw new UnsupportedOperationException();
    }

}

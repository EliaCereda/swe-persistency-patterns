package model.repository;

import model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private User load(ResultSet rs) {
        throw new UnsupportedOperationException();
    }

    // Mutators

    public void insert(User user) {

    }

    public void update(User user) {

    }

    public void delete(User user) {

    }

    // Queries

    public List<User> findUsers() {
        User u1 = new User();
        u1.setUsername("elia");
        u1.setPassword("password");
        u1.setName("Elia Cereda");

        return List.of(u1);
    }

    public List<User> findUsersByName(String name) {
        throw new UnsupportedOperationException();
    }

    public List<User> findUsersByStreetAddress(String streetAddress) {
        throw new UnsupportedOperationException();
    }

    public List<User> findUsersByBestFriend(/* FIXME */) {
        throw new UnsupportedOperationException();
    }

}

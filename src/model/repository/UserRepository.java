package model.repository;

import model.User;

import java.sql.ResultSet;
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

    List<User> findUsers() {
        throw new UnsupportedOperationException();
    }

    List<User> findUsersByName(String name) {
        throw new UnsupportedOperationException();
    }

    List<User> findUsersByStreetAddress(String streetAddress) {
        throw new UnsupportedOperationException();
    }

    List<User> findUsersByBestFriend(/* FIXME */) {
        throw new UnsupportedOperationException();
    }



}

package model.repository;

import model.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> {
    protected Database db;

    public Repository(Database db) {
        this.db = db;
    }

    // Data Mapping

    protected abstract T load(ResultSet rs) throws SQLException;

    // Queries

    protected List<T> findAllByQuery(String query, Object... queryParams) {
        List<T> users = new ArrayList<>();

        try (Connection connection = db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            for (int i = 0; i < queryParams.length; i++) {
                stmt.setObject(i + 1, queryParams[i]);
            }

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                T entity = load(results);
                users.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public T findOneByQuery(String query, Object... queryParams) {
        List<T> entities = findAllByQuery(query, queryParams);

        if (!entities.isEmpty()) {
            return entities.get(0);
        } else {
            return null;
        }
    }
}

package ru.anarbek.repository;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import ru.anarbek.entity.Keep;

import java.sql.SQLException;
import java.util.List;

public class DBKeepRepository implements Repository {

    private final String url = "jdbc:sqlite:keep.db";

    private ConnectionSource source;

    private Dao<Keep, String> dao;

    public DBKeepRepository() throws RepositoryException {
        try {
            source = new JdbcConnectionSource(url);
            dao = DaoManager.createDao(source, Keep.class);
        } catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    public List<Keep> getAll() throws RepositoryException {
        try {
            return dao.queryForAll();
        } catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }
}

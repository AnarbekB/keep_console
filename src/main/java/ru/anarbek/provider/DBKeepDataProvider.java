package ru.anarbek.provider;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import ru.anarbek.entity.Keep;
import ru.anarbek.helper.Env;
import ru.anarbek.helper.EnvException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DBKeepDataProvider implements DataProvider {

    private Dao<Keep, String> dao;

    public DBKeepDataProvider() throws ProviderException {
        try {
            String url = Env.getInstance().getEnv("db.sqllite.url");
            ConnectionSource source = new JdbcConnectionSource(url);
            dao = DaoManager.createDao(source, Keep.class);
        } catch (SQLException | EnvException | IOException exception) {
            throw new ProviderException(exception.getMessage());
        }
    }

    @Override
    public List<Keep> getAll() throws ProviderException {
        try {
            return dao.queryForAll();
        } catch (SQLException exception) {
            throw new ProviderException(exception.getMessage());
        }
    }

    @Override
    public Keep create(String title, String $data) throws ValidationException, ProviderException {
        if (title == null || $data == null) {
            throw new ValidationException("Title and data is required");
        }

        Keep keep = new Keep();
        keep.setTitle(title);
        keep.setData($data);

        try {
            int result = dao.create(keep);
            if (result == 0) {
                return null;
            } else {
                return keep;
            }
        } catch (SQLException exception) {
            throw new ProviderException("Error create: " + exception.getMessage());
        }
    }
}

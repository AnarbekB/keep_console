package ru.anarbek.db;

import ru.anarbek.helper.Env;
import ru.anarbek.helper.EnvException;

import java.io.IOException;
import java.sql.*;

public class SQLiteConnection {
    private Connection connection;
    private static SQLiteConnection instance;

    private SQLiteConnection() {

    }

    public static SQLiteConnection connect() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new SQLiteConnection();
            Class.forName("org.sqlite.JDBC");

            try {
                String url = Env.getInstance().getEnv("db.sqllite.url");
                instance.connection = DriverManager.getConnection(url);
            } catch (EnvException | IOException e) {
                throw new SQLException(e.getMessage());
            }
        }

        return instance;
    }

    public SQLiteConnection getConnection() {
        return this;
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    public void executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public void executeQueryWithParams(String query, String[] params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for(int i = 1; i <= params.length; i++) {
            statement.setString(i, params[i - 1]);
        }
        statement.executeUpdate();
    }

    public String getResult(String query, String $column) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet.getString($column);
    }
}

package ru.anarbek.db;

import java.sql.*;

//todo all field it string
public class SQLiteConnection {
    private Connection connection;
    private static SQLiteConnection instance;

    public static SQLiteConnection connect() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new SQLiteConnection();
            //todo move to resource file
            Class.forName("org.sqlite.JDBC");
            instance.connection = DriverManager.getConnection("jdbc:sqlite:keep.db");
        }

        return instance;
    }

    public SQLiteConnection getConection() {
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

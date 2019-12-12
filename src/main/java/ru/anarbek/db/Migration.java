package ru.anarbek.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Migration {

    private String migrationFolder;

    private SQLiteConnection connection;

    public Migration(String migrationFilesPath) throws Exception {
        this.migrationFolder = migrationFilesPath;
        this.connection = SQLiteConnection.connect();
    }

    public void migrate() throws MigrationException{
        String currentVersion = this.getMigrationCurrentVersion();

        ArrayList<File> files = this.getMigrate(currentVersion);

        for (File file : files) {

            try {
                String sql = this.getSQL(file);

                this.connection.executeQuery(sql);
                String migrationVersion = file.getName().substring(0, 3);
                upVersion(migrationVersion);
            } catch (Exception e) {
                throw new MigrationException("Failed run migration: " + file.getName());
            }
        }
        try {
            this.connection.disconnect();
        } catch (SQLException e) {
            throw new MigrationException("Failed disconnect connection to DB");
        }
    }

    /**
     * currentVersion - string format: 000, 001, 010 etc.
     * or null if need all migrate files
     */
    public ArrayList<File> getMigrate(String currentVersion) {
        File migrationsFolder = new File(migrationFolder);
        ArrayList<File> files = new ArrayList<>();

        File[] fileList = migrationsFolder.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                String migrationVersion = file.getName().substring(0, 3);
                if (currentVersion == null || currentVersion.compareTo(migrationVersion) < 0) {
                    files.add(file);
                }
            }
        }

        return files;
    }

    private String getSQL(File migration) throws MigrationException, IOException {
        if (migration == null || !migration.isFile()) {
            throw new MigrationException("Migration is not file");
        }

        List<String> list = Files.readAllLines(migration.toPath());
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : list) {
            stringBuilder.append(line);
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    private void upVersion(String version) throws MigrationException {
        if (version == null) {
            throw new MigrationException("Version not be null");
        }

        String currentVersion = this.getMigrationCurrentVersion();
        try {
            if (currentVersion == null) {
                String sql = "INSERT INTO migrations(version) VALUES (?)";
                String[] params = {version};
                this.connection.executeQueryWithParams(sql, params);
            } else {
                String sql = "UPDATE migrations SET version = ?";
                String[] params = {version};
                this.connection.executeQueryWithParams(sql, params);
            }
        } catch (Exception e) {
            throw new MigrationException("Failed up migration version");
        }
    }

    private String getMigrationCurrentVersion() {
        try {
            String currentVersionSQL = "SELECT version FROM migrations";
            return this.connection.getResult(currentVersionSQL, "version");
        } catch (SQLException e) {
            return null;
        }
    }
}

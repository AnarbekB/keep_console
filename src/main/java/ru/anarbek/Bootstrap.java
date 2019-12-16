package ru.anarbek;

import ru.anarbek.db.Migration;
import ru.anarbek.db.MigrationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Bootstrap {

    private static Migration migration;

    public static void init() throws BootstrapApplicationException {
        if (migration == null) {
            try {
                migration = new Migration("src/main/resources/migrations");
            } catch (Throwable throwable) {
                for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
                    System.out.println(stackTraceElement.getMethodName() + " " + stackTraceElement.getFileName());
                }
                throw new BootstrapApplicationException(
                        "Error load migration for first run application: " + throwable.getMessage()
                );
            }
        }

        boolean firstRun;
        try {
            firstRun = isFirstRun();
        } catch (IOException exception) {
            throw new BootstrapApplicationException("Error detect first run application");
        }

        if (firstRun) {
            try {
                migration.migrate();
            } catch (MigrationException e) {
                throw new BootstrapApplicationException(e.getMessage());
            }
        }
    }

    private static boolean isFirstRun() throws IOException {
        File file = new File("src/main/resources/first_run");

        if (file.createNewFile()) {
            Files.write(file.toPath(), "true".getBytes());
        }

        List<String> list = Files.readAllLines(file.toPath());
        if (list.get(0).equals("true")) {
            Files.write(file.toPath(), "false".getBytes());

            return true;
        } else {
            return false;
        }
    }
}

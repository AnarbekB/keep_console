package ru.anarbek.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Env {

    private static Env instance;

    private String path = "keep_console.properties";

    private Properties properties;

    private Env() throws IOException, EnvException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("keep_console.properties");
        if (input == null) {
            throw new EnvException("Not load config file");
        }

        properties = new Properties();
        properties.load(input);
    }

    public static Env getInstance() throws IOException, EnvException {
        if (instance == null) {
            instance = new Env();
        }

        return instance;
    }

    public String getEnv(String key) {
        return properties.getProperty(key);
    }
}

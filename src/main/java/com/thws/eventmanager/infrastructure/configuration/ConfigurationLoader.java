package com.thws.eventmanager.infrastructure.configuration;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationLoader {
    private static final Properties properties = new Properties();

    static {
        Dotenv dotenv = Dotenv.configure()
                .directory("./")  // Hauptverzeichnis
                .ignoreIfMalformed()  // Ignoriere Fehler
                .ignoreIfMissing()    // Ignoriere fehlende .env
                .load();

        try (InputStream input = ConfigurationLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("application.properties not found");
            }
            properties.load(input);

            // Ersetze Platzhalter (${...}) mit Werten aus der .env
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                if (value != null && value.startsWith("${") && value.endsWith("}")) {
                    String envKey = value.substring(2, value.length() - 1); // Extrahiere den Namen z. B. STRIPE_API_KEY_TEST
                    String envValue = dotenv.get(envKey); // Hole den Wert aus der .env-Datei
                    if (envValue != null) {
                        properties.setProperty(key, envValue); // Setze den aufgelösten Wert
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

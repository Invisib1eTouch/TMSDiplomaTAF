package core;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(PropertyReader.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCatalogBaseUrl() {
        return properties.getProperty("catalog.base.url");
    }

    public static String getProfileBaseUrl() {
        return properties.getProperty("profile.base.url");
    }

    public static String getBrowserName() {
        return properties.getProperty("browser").toLowerCase();
    }
}

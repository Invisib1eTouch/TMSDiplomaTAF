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

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getUrlPrefix(String prefixName) {
        return properties.getProperty(prefixName);
    }

    public static String getBrowserName() {
        return properties.getProperty("browser").toLowerCase();
    }

    public static String getApiPath() {
        return properties.getProperty("api.path").toLowerCase();
    }

    public static String getDbUrl() {
        return properties.getProperty("db_url");
    }

    public static String getDbUser() {
        return properties.getProperty("db_user");
    }

    public static String getDbPassword() {
        return properties.getProperty("db_psw");
    }

}

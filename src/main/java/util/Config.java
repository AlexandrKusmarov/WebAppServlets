package util;

import java.io.InputStream;
import java.util.Properties;

public class Config {
        static final String DB_URL = "db.url";
        static final String DB_LOGIN = "db.login";
        static final String DB_PASSWORD = "db.password";

        private static Properties properties = new Properties();

        public synchronized static String getProperty(String name) {
            if (properties.isEmpty()) {
                try (InputStream is = Config.class.getClassLoader()
                        .getResourceAsStream("config.properties")) {

                    properties.load(is);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
            }
            return properties.getProperty(name);
        }
    }


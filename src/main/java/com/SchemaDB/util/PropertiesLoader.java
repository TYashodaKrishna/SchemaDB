package com.SchemaDB.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static Properties properties;

    public PropertiesLoader() throws IOException {
        properties = new Properties();
        InputStream inputStream = PropertiesLoader.class
                .getClassLoader().getResourceAsStream("config.properties");
        if(inputStream != null) {
            properties.load(inputStream);
        }
        properties.putAll(System.getProperties());
    }

    public static Properties getProperties() {
        return properties;
    }
}

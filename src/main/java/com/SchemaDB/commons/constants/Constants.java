package com.SchemaDB.commons.constants;

import com.SchemaDB.util.PropertiesLoader;

import java.io.IOException;
import java.util.Properties;

public class Constants {

    private static Properties properties;

    static {
        try {
            properties = new PropertiesLoader().getProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Constants() {}

    public static final String database = properties.getProperty("database", "redis");

    public static final String REDIS_HOST = properties.getProperty("redis.host");
    public static final Integer REDIS_PORT = Integer.valueOf(properties.getProperty("redis.port"));
    public static final Integer REDIS_TIMEOUT = Integer.valueOf(properties.getProperty("redis.timeout"));

    public static final Integer MONGO_PORT = Integer.valueOf(properties.getProperty("mongo.port"));
    public static final String MONGO_HOST = properties.getProperty("mongo.host");
}

package com.SchemaDB.dao;

import com.SchemaDB.commons.constants.Constants;

public class DaoProvider {

    public StoreDao get() {
        String database = Constants.database;
        if(database.equals("redis")) {
            return new RedisDao();
        }
        return new MongoDao();
    }
}

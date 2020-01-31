package com.SchemaDB.dao;

import com.SchemaDB.commons.constants.Constants;
import com.google.inject.Provider;

public class DaoProvider implements Provider<StoreDao> {

    @Override
    public StoreDao get() {
        String database = Constants.database;
        if(database.equals("redis")) {
            return new RedisDao();
        }
        return new MongoDao();
    }
}

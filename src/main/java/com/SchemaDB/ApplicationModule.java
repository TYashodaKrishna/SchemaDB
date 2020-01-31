package com.SchemaDB;

import com.SchemaDB.dao.DaoProvider;
import com.SchemaDB.dao.StoreDao;
import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(StoreDao.class).toProvider(DaoProvider.class).asEagerSingleton();
    }
}

package com.SchemaDB.service.impl;

import com.SchemaDB.dao.StoreDao;
import com.SchemaDB.exceptions.SchemaNotFoundException;
import com.SchemaDB.exceptions.StoreException;
import com.SchemaDB.model.SchemaModel;
import com.SchemaDB.service.SchemaService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SchemaServiceImpl implements SchemaService {

    private StoreDao storeDao;

    public SchemaServiceImpl(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @Override
    public void storeSchema(String siteKey, SchemaModel siteSchema) throws StoreException {
        storeDao.storeSchema(siteKey, siteSchema);
    }

    @Override
    public SchemaModel getSchema(String siteKey) throws SchemaNotFoundException, IOException {
        if(storeDao.isExists(siteKey)) {
            SchemaModel schema = storeDao.getSchema(siteKey);
            return schema;
        } else {
            throw new SchemaNotFoundException("Schema not found for siteKey: " + siteKey);
        }
    }

    @Override
    public void deleteSchema(String siteKey) throws SchemaNotFoundException {
        if(storeDao.isExists(siteKey)) {
            storeDao.deleteSchema(siteKey);
        } else {
            throw new SchemaNotFoundException("Schema not found for siteKey: " + siteKey);
        }
    }

    @Override
    public void updateSchema(String siteKey, SchemaModel siteSchema) throws SchemaNotFoundException, StoreException {
        if (storeDao.isExists(siteKey)) {
            storeDao.deleteSchema(siteKey);
            storeDao.storeSchema(siteKey, siteSchema);
        } else {
            throw new SchemaNotFoundException("Schema does not exist for the siteKey: " + siteKey + " to update");
        }
    }
}

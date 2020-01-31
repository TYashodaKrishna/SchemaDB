package com.SchemaDB.dao;

import com.SchemaDB.exceptions.StoreException;
import com.SchemaDB.model.SchemaModel;

import java.io.IOException;

public interface StoreDao {
    Boolean isExists(String siteKey);

    SchemaModel getSchema(String siteKey) throws IOException;

    void storeSchema(String siteKey, SchemaModel siteSchema) throws StoreException;

    void deleteSchema(String siteKey);
}

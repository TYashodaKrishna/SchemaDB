package com.SchemaDB.service;

import com.SchemaDB.exceptions.SchemaNotFoundException;
import com.SchemaDB.exceptions.StoreException;
import com.SchemaDB.model.SchemaModel;

import java.io.IOException;

public interface SchemaService {
    void storeSchema(String siteKey, SchemaModel siteSchema) throws StoreException;

    SchemaModel getSchema(String siteKey) throws SchemaNotFoundException, IOException;

    void deleteSchema(String siteKey) throws SchemaNotFoundException;

    void updateSchema(String siteKey, SchemaModel siteSchema) throws SchemaNotFoundException, StoreException;
}

package com.SchemaDB.dao;

import com.SchemaDB.model.SchemaModel;
import com.SchemaDB.util.MongoStandAloneClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;

public class MongoDao implements StoreDao {

    @Autowired
    private MongoStandAloneClient mongoClient;
    private MongoDatabase db;

    @Override
    public Boolean isExists(String siteKey) {
        db = mongoClient.getMongoDatabase();
        MongoCollection<SchemaModel> collection = db.getCollection(siteKey, SchemaModel.class);
        if (collection.find().first() != null) {
            return true;
        }
        return false;
    }

    @Override
    public SchemaModel getSchema(String siteKey) {
        db = mongoClient.getMongoDatabase();
        MongoCollection<SchemaModel> collection = db.getCollection(siteKey, SchemaModel.class);
        SchemaModel schema = collection.find().first();
        return schema;
    }

    @Override
    public void storeSchema(String siteKey, SchemaModel siteSchema) {
        db = mongoClient.getMongoDatabase();
        db.createCollection(siteKey);
        MongoCollection<SchemaModel> collection = db.getCollection(siteKey, SchemaModel.class);
        collection.insertOne(siteSchema);
    }

    @Override
    public void deleteSchema(String siteKey) {
        db = mongoClient.getMongoDatabase();
        MongoCollection collection = db.getCollection(siteKey);
        collection.drop();
    }
}

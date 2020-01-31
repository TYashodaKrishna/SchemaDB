package com.SchemaDB.dao;

import com.SchemaDB.model.SchemaModel;
import com.SchemaDB.util.MongoStandAloneClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDao implements StoreDao {

    private MongoStandAloneClient mongoStandAloneClient;
    private MongoDatabase db;

    public MongoDao() {
        mongoStandAloneClient = new MongoStandAloneClient();
        db = mongoStandAloneClient.getMongoDatabase();
    }
    @Override
    public Boolean isExists(String siteKey) {
        MongoCollection<SchemaModel> collection = db.getCollection(siteKey, SchemaModel.class);
        if (collection.find().first() != null) {
            return true;
        }
        return false;
    }

    @Override
    public SchemaModel getSchema(String siteKey) {
        MongoCollection<SchemaModel> collection = db.getCollection(siteKey, SchemaModel.class);
        SchemaModel schema = collection.find().first();
        return schema;
    }

    @Override
    public void storeSchema(String siteKey, SchemaModel siteSchema) {
        db.createCollection(siteKey);
        MongoCollection<SchemaModel> collection = db.getCollection(siteKey, SchemaModel.class);
        collection.insertOne(siteSchema);
    }

    @Override
    public void deleteSchema(String siteKey) {
        MongoCollection collection = db.getCollection(siteKey);
        collection.drop();
    }
}

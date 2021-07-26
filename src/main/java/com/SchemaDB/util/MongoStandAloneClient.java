package com.SchemaDB.util;

import com.SchemaDB.commons.constants.Constants;
import com.SchemaDB.model.SchemaField;
import com.SchemaDB.model.SchemaModel;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

@Slf4j
public class MongoStandAloneClient {

    public static final String SCHEMA_DB_NAME = "SchemaDB";
    private MongoDatabase database;
    MongoClient mongoClient;

    public MongoStandAloneClient() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().register(SchemaField.class).register(SchemaModel.class).automatic(true).build()));
        String port = String.valueOf(Constants.MONGO_PORT);
        mongoClient = new MongoClient(new MongoClientURI("mongodb://"+Constants.MONGO_HOST+":"+ port));
        database = mongoClient.getDatabase(SCHEMA_DB_NAME).withCodecRegistry(pojoCodecRegistry);
    }

    public MongoDatabase getMongoDatabase() {
        return database;
    }

    public void returnClient() {
        mongoClient.close();
    }
}

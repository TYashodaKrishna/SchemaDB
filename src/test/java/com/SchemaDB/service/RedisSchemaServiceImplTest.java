package com.SchemaDB.service;

import com.SchemaDB.dao.RedisDao;
import com.SchemaDB.exceptions.SchemaNotFoundException;
import com.SchemaDB.exceptions.StoreException;
import com.SchemaDB.model.SchemaModel;
import com.SchemaDB.service.impl.SchemaServiceImpl;
import com.SchemaDB.util.RedisStandAloneClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.*;

import static org.junit.Assert.*;

public class RedisSchemaServiceImplTest {

    RedisStandAloneClient redisClient = new RedisStandAloneClient();
    private Jedis jedis;
    private SchemaService service = new SchemaServiceImpl(new RedisDao());

    @Before
    public void init() {
        jedis = redisClient.getClient();
    }

    @Test
    public void CRUD_schema() throws FileNotFoundException {
        String siteKey = "test-site";
        try {
            service.getSchema(siteKey);
        } catch (Exception e) {
            assertTrue(e instanceof SchemaNotFoundException);
        }

        BufferedReader reader = new BufferedReader(new FileReader("/Users/unbxd/tyk/SchemaDB/src/main/resources/schema/schema1.json"));
        Gson gson = new GsonBuilder().create();
        SchemaModel schema = gson.fromJson(reader, SchemaModel.class);
        try {
            service.storeSchema(siteKey, schema);
            assertEquals(schema, service.getSchema(siteKey));
        } catch(Exception e) {
            assertTrue(e instanceof StoreException);
        }

        try {
            service.storeSchema(siteKey, schema);
            service.deleteSchema(siteKey);
            service.getSchema(siteKey);
        } catch (Exception e) {
            assertTrue(e instanceof SchemaNotFoundException);
        }

        BufferedReader reader2 = new BufferedReader(new FileReader("/Users/unbxd/tyk/SchemaDB/src/main/resources/schema/schema2.json"));
        SchemaModel schema2 = gson.fromJson(reader2, SchemaModel.class);
        try {
            service.storeSchema(siteKey, schema);
            assertEquals(schema, service.getSchema(siteKey));
            service.updateSchema(siteKey, schema2);
            assertEquals(schema2, service.getSchema(siteKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        if(jedis != null) {
            jedis.close();
        }
        redisClient.stopServer();
    }
}

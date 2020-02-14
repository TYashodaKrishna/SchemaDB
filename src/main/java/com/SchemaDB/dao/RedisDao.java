package com.SchemaDB.dao;

import com.SchemaDB.exceptions.StoreException;
import com.SchemaDB.model.SchemaModel;
import com.SchemaDB.util.RedisStandAloneClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.io.IOException;

@Slf4j
public class RedisDao implements StoreDao {
    @Autowired
    private RedisStandAloneClient redisClient;
    private static ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public Boolean isExists(String siteKey) {
        Jedis jedis = redisClient.getClient();
        Boolean exists = jedis.exists(siteKey);
        return exists;
    }

    @Override
    public SchemaModel getSchema(String siteKey) throws IOException {
        Jedis jedis = redisClient.getClient();
        String schema = jedis.get(siteKey);
        return MAPPER.readValue(schema, SchemaModel.class);
    }

    @Override
    public void storeSchema(String siteKey, SchemaModel siteSchema) throws StoreException {
        try{
            Jedis jedis = redisClient.getClient();
            String schema = MAPPER.writeValueAsString(siteSchema);
            jedis.set(siteKey, schema);
        } catch (JsonProcessingException e) {
            throw new StoreException(e);
        }
    }

    @Override
    public void deleteSchema(String siteKey) {
        Jedis jedis = redisClient.getClient();
        jedis.del(siteKey);
    }
}

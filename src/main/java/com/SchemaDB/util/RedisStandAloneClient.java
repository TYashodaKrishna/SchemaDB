package com.SchemaDB.util;

import com.SchemaDB.commons.constants.Constants;
import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;

public class RedisStandAloneClient {

    private static RedisStandAloneClient instance;
    public Jedis jedis;
    private static RedisServer redisServer;

    static {
        try {
            redisServer = new RedisServer(Constants.REDIS_PORT);
            redisServer.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Jedis getClient() {
        jedis = new Jedis(Constants.REDIS_HOST, Constants.REDIS_PORT, Constants.REDIS_TIMEOUT);
        return jedis;
    }

    public void stopServer() {
        if(jedis != null) {
            jedis.close();
        }
        redisServer.stop();
    }
}

package com.SchemaDB;

import com.SchemaDB.dao.DaoProvider;
import com.SchemaDB.dao.StoreDao;
import com.SchemaDB.service.SchemaService;
import com.SchemaDB.service.impl.SchemaServiceImpl;
import com.SchemaDB.util.MongoStandAloneClient;
import com.SchemaDB.util.RedisStandAloneClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    StoreDao storeDao() {
        return new DaoProvider().get();
    }

    @Bean
    @Autowired
    SchemaService schemaService(StoreDao storeDao) {
        return new SchemaServiceImpl(storeDao);
    }

    @Bean
    RedisStandAloneClient redisStandAloneClient() {
        return new RedisStandAloneClient();
    }

    @Bean
    MongoStandAloneClient mongoStandAloneClient() {
        return new MongoStandAloneClient();
    }
}

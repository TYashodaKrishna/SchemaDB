package com.SchemaDB;

import com.SchemaDB.service.SchemaService;
import com.SchemaDB.service.impl.SchemaServiceImpl;
import com.google.inject.Guice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    SchemaService schemaService() {
        return Guice.createInjector(new ApplicationModule())
                .getInstance(SchemaServiceImpl.class);
    }
}

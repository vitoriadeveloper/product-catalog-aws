package com.vitoriadeveloper.catolog_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {
    @Value("${spring.data.mongodb.uri}")
    private String mongoURI;

    @Bean
    public MongoDatabaseFactory mongoConfigure() {
        return new SimpleMongoClientDatabaseFactory(mongoURI);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoConfigure());
    }
}

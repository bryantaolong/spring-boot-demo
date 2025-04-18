package com.bryan.springbootdemo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * ClassName: MongoDBConfig
 * Package: com.bryan.springbootdemo.config
 * Description:
 * Author: Bryan Long
 * Create: 2025/1/25 - 19:32
 * Version: v1.0
 */
@Configuration
public class MongodbConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Bean
    public MongoOperations mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "blog");
    }

}

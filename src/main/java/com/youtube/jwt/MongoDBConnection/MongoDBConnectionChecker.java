package com.youtube.jwt.MongoDBConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDBConnectionChecker implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Check MongoDB connection when the application starts
        try {
            mongoTemplate.getDb().getName();
            System.out.println("Connected to MongoDB!");
        } catch (Exception e) {
            System.err.println("MongoDB connection error: " + e.getMessage());
        }
    }
}
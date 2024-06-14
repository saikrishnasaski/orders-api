package com.lights5.services.orders.api.config;

import com.lights5.services.orders.api.service.HostAvailability;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.data.mongo.MongoHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
public class CustomMongoHealthIndicator extends MongoHealthIndicator {

    private final MongoTemplate mongoTemplate;

    @Value("${spring.data.mongodb.host}")
    private String mongodbHost;

    @Value("${spring.data.mongodb.port}")
    private int port;

    public CustomMongoHealthIndicator(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
        this.mongoTemplate = mongoTemplate;
    }

    public void doHealthCheck(Health.Builder builder) throws Exception {
        boolean isServerAvailable = HostAvailability.isAvailable(mongodbHost, port);
        if (isServerAvailable) {
            Document result = mongoTemplate.executeCommand("{ isMaster: 1 }");
            builder.up().withDetail("maxWireVersion", result.getInteger("maxWireVersion"));
        }
        else {
            log.error("MongoDB Server is down.");
            builder.down();
        }
    }
}

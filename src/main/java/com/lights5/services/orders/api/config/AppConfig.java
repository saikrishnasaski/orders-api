package com.lights5.services.orders.api.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.boot.actuate.data.mongo.MongoHealthIndicator;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    MongoHealthIndicator mongoHealthIndicator(MongoTemplate mongoTemplate) {

        return new CustomMongoHealthIndicator(mongoTemplate);
    }

    @Bean
    AdminClient kafkaAdminClient(KafkaProperties kafkaProperties) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", kafkaProperties.getBootstrapServers());
        properties.put("request.timeout.ms", 3000);
        properties.put("connections.max.idle.ms", 5000);
        return AdminClient.create(properties);
    }
}

package com.lights5.services.orders.api.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.Node;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaHealthIndicator extends AbstractHealthIndicator {

    private final AdminClient kafkaAdminClient;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {

        Collection<Node> nodes = kafkaAdminClient.describeCluster()
                .nodes().get(3, TimeUnit.SECONDS);

        if (!nodes.isEmpty()) {
            log.error("Kafka Server is up with nodes {}", nodes.size());
            builder.up();
        }
        else {
            log.error("Kafka Server is down");
            builder.down();
        }
    }
}

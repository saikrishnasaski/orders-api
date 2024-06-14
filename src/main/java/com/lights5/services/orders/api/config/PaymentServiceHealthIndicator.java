package com.lights5.services.orders.api.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentServiceHealthIndicator extends AbstractHealthIndicator {

    private final EndpointsRegistry endpointsRegistry;

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        try {
            String healthCheckURL = endpointsRegistry.getHealthCheckURL("payment-service");
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            CloseableHttpResponse response = httpClient.execute(new HttpGet(healthCheckURL));
            if (response.getCode() > 500) {
                log.error("Payment Service is down");
            }
            else {
                builder.up();
            }
        } catch (IOException ex) {
            builder.down();
        }
    }
}

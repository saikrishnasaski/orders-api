package com.lights5.services.orders.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class EndpointsRegistry {

    private Map<String, ServiceEndpoint> client;

    @Getter
    @Setter
    static class ServiceEndpoint {

        private String host;
        private Map<String, String> paths;
    }

    public String getHealthCheckURL(String serviceName) {
        ServiceEndpoint endpoint = this.client.get(serviceName);
        if (endpoint != null) {
            String healthCheckPath = endpoint.getPaths().get("health-check");
            String host = endpoint.getHost();

            return host + healthCheckPath;
        }
        return null;
    }
}

package com.lights5.services.orders.api;

import com.lights5.services.orders.api.config.EndpointsRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrdersApiApplication implements CommandLineRunner {

	@Autowired
	private EndpointsRegistry endpointsRegistry;

	public static void main(String[] args) {
		SpringApplication.run(OrdersApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}

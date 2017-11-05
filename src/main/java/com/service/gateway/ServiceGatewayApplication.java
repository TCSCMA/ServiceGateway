package com.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.service.gateway.filter.GatewayPreFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ServiceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceGatewayApplication.class, args);
	}
	
	@Bean
    public GatewayPreFilter preFilter() {
        return new GatewayPreFilter();
    }
}

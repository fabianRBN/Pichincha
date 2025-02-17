package com.ftoapanta.pichincha.gatewey.beans;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayBeans {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(
                        route -> route
                                .path("/client-crud/client")
                                .uri("http://localhost:8081")
                )
                .route(
                        route -> route
                                .path("/account-crud/account/client/*")
                                .uri("http://localhost:8082")
                )
                .build();
    }
}

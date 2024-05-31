package ch.hearc.jee24.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("posts-service", r -> r.path("/posts/**")
            .filters(f -> f.stripPrefix(1))
            .uri("lb://POSTS-SERVICE"))
            .route("users-service", r -> r.path("/users/**")
            .filters(f -> f.stripPrefix(1))
            .uri("lb://USERS-SERVICE"))
            .build();
    }
}

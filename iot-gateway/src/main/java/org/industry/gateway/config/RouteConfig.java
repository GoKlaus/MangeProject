package org.industry.gateway.config;

import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 路由配置
 */
@Configuration
public class RouteConfig {

    /**
     * @return
     */
    @Bean
    public KeyResolver hostKeyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostString());
    }

    /**
     * Redis 令牌桶 限流
     *
     * @return
     */
    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(100, 2000);
    }

    /**
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        Consumer<RequestRateLimiterGatewayFilterFactory.Config> requestRateLimiterFilterFactory = l -> l.setKeyResolver(hostKeyResolver()).setRateLimiter(redisRateLimiter());
        Consumer<SpringCloudCircuitBreakerFilterFactory.Config> circuitBreakerFilterFactory = h -> h.setName("default").setFallbackUri("forward:/fallback");
        return builder.routes()
                .route("token_salt",
                        r -> r.path("/api/v3/token/salt")
                                .filters(f -> f.setPath("/auth/token/salt")
                                        .requestRateLimiter(requestRateLimiterFilterFactory)
                                        .circuitBreaker(circuitBreakerFilterFactory))
                                .uri("lb://iot-center-auth")
                )
                .route("generate_token",
                        r -> r.path("/api/v3/token/generate")
                                .filters(f -> f.setPath("/auth/token/generate")
                                        .requestRateLimiter(requestRateLimiterFilterFactory)
                                        .circuitBreaker(circuitBreakerFilterFactory))
                                .uri("lb://iot-center-auth")
                )
                .route("check_token",
                        r -> r.path("/api/v3/token/check")
                                .filters(f -> f.setPath("/auth/token/check")
                                        .requestRateLimiter(requestRateLimiterFilterFactory)
                                        .circuitBreaker(circuitBreakerFilterFactory))
                                .uri("lb://iot-center-auth")
                )
                .route("cancel_token",
                        r -> r.path("/api/v3/token/cancel")
                                .filters(f -> f.setPath("/auth/token/cancel")
                                        .requestRateLimiter(requestRateLimiterFilterFactory)
                                        .circuitBreaker(circuitBreakerFilterFactory))
                                .uri("lb://iot-center-auth")
                )
                .route("register_user",
                        r -> r.path("/api/v3/register")
                                .filters(f -> f.setPath("/auth/user/add")
                                        .requestRateLimiter(requestRateLimiterFilterFactory)
                                        .circuitBreaker(circuitBreakerFilterFactory))
                                .uri("lb://iot-center-auth")
                )
                .build();
    }
}

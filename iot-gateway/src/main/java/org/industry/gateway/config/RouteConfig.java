package org.industry.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

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
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("generate_token",
                        r -> r.path("/api/v3/token/generate")
                                .filters(
                                        f -> f.setPath("/auth/token/generate")
                                                .requestRateLimiter(l -> l.setKeyResolver(hostKeyResolver()).setRateLimiter(redisRateLimiter()))
                                                .circuitBreaker(h -> h.setName("default").setFallbackUri("forward:/fallback"))
                                ).uri("lb://dc3-center-auth"))
                .build();
    }
}

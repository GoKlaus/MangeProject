package org.industry.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.industry.gateway.fallback.GatewayFallback;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class GatewayConfig {

    private GatewayFallback gatewayFallback;

    public GatewayConfig(GatewayFallback fallback) {
        this.gatewayFallback = fallback;
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

//@Bean
//    public decoder
}

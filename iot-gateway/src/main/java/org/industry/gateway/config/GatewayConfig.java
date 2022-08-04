package org.industry.gateway.config;

import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.industry.gateway.fallback.GatewayFallback;
import org.industry.gateway.filter.BlackIpGlobalFilter;
import org.industry.gateway.filter.factory.AuthenticatorGatewayFilterFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

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

    @Bean
    public BlackIpGlobalFilter blackIpGlobalFilter() {
        return new BlackIpGlobalFilter();
    }

    @Bean
    public AuthenticatorGatewayFilterFactory authenticatorGatewayFilterFactory() {
        return new AuthenticatorGatewayFilterFactory();
    }

    @Bean
    public Decoder feignDecoder(ObjectProvider<HttpMessageConverterCustomizer> customizer) {
        return new ResponseEntityDecoder(
                new SpringDecoder(
                        () -> new HttpMessageConverters(new GatewayMappingJackson2HttpMessageConverter()),
                        customizer));

    }

    public static class GatewayMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        GatewayMappingJackson2HttpMessageConverter() {
            List<MediaType> list = new ArrayList<>();
            list.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=utf-8"));
            list.add(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"));
            setSupportedMediaTypes(list);
        }
    }
}

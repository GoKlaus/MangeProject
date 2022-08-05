package org.industry.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.R;
import org.industry.api.auth.feign.BlackIpClient;
import org.industry.gateway.utils.GatewayUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Slf4j
public class BlackIpGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private BlackIpClient blackIpClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String remoteIp = GatewayUtil.getRemoteIp(request);
        R<Boolean> result = blackIpClient.checkBlackIpValid(remoteIp);
        if (result.isOk()) {
            log.error("Forbidden Ip: {}", remoteIp);
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
        log.info("Remote Ip: {}; Request url: {}; Response code: {}", remoteIp, request.getURI().getRawPath(), exchange.getResponse().getStatusCode());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

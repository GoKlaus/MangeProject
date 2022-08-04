package org.industry.gateway.filter.factory;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.exception.ServiceException;
import org.industry.common.model.Tenant;
import org.industry.common.utils.Dc3Util;
import org.industry.common.utils.JsonUtil;
import org.industry.feign.TenantClient;
import org.industry.feign.TokenClient;
import org.industry.gateway.utils.GatewayUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@Component
public class AuthenticatorGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return new AuthenticatorGatewayFilter();
    }

    @Component
    static class AuthenticatorGatewayFilter implements GatewayFilter {

        private static AuthenticatorGatewayFilter gatewayFilter;

        @Resource
        private TenantClient tenantClient;

        @Resource
        private TokenClient tokenClient;

        @PostConstruct
        public void init() {
            gatewayFilter = this;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            ServerHttpRequest request = exchange.getRequest();
            try {
                String cookie = GatewayUtil.getRequestCookie(request, ServiceConstant.Header.X_AUTH_TOKEN);
                Login login = JsonUtil.parseObject(Dc3Util.decode(cookie), Login.class);
                log.debug("Request cookie: {}", login);

                R<Tenant> tenantR = gatewayFilter.tenantClient.selectByName(login);
                if (!tenantR.isOk() || !tenantR.getData().getEnable()) {
                    throw new ServiceException("Invalid tenant");
                }

                R<Long> validR = gatewayFilter.tokenClient.checkTokenValid(login);
                if (!validR.isOk()) {
                    throw new ServiceException("Invalid token");
                }
                Tenant tenant = tenantR.getData();
                log.debug("Request tenant: {}", tenant);

                ServerHttpRequest build = request.mutate()
                        .headers(httpHeaders -> {
                            httpHeaders.set(ServiceConstant.Header.X_AUTH_TENANT_ID, tenant.getId());
                            httpHeaders.set(ServiceConstant.Header.X_AUTH_TENANT, login.getTenant());
                            httpHeaders.set(ServiceConstant.Header.X_AUTH_USER, login.getName());
                        }).build();
                exchange.mutate().request(build).build();
            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                response.setStatusCode(HttpStatus.FORBIDDEN);
                log.error(e.getMessage(), e);

                DataBuffer dataBuffer = response.bufferFactory().wrap(JsonUtil.toJsonBytes(R.fail(e.getMessage())));
                return response.writeWith(Mono.just(dataBuffer));

            }
            return chain.filter(exchange);
        }
    }
}

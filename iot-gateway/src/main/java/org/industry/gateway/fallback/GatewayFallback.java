package org.industry.common.base.gateway.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 *
 */
@Slf4j
@Component
public class GatewayFallback implements HandlerFunction<ServerResponse> {

    /**
     * 回调
     *
     * @param request
     * @return
     */
    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        Optional<Object> originalUris = request.attribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        originalUris.ifPresent(originalUri -> log.error("Request Url: {}, Service Fallback", originalUri));
        return ServerResponse
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("No available server for this request"));
    }
}

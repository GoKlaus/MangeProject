package org.industry.common.base.gateway.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关异常通用处理器，只作用在webflux环境下，
 * 优先级低于{@link org.springframework.web.server.handler.ResponseStatusExceptionHandler}执行
 */
@Slf4j
@Order(-1)
@Component
@AllArgsConstructor
public class ExceptionHandlerConfig implements ErrorWebExceptionHandler {

    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            try {
                return dataBufferFactory.wrap(objectMapper.writeValueAsBytes(ex.getMessage()));
            } catch (JsonProcessingException e) {
                log.error(ex.getMessage(), ex);
                return dataBufferFactory.wrap(new byte[0]);
            }
        }));
    }
}

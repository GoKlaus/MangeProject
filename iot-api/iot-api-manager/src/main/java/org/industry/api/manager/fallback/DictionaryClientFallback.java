package org.industry.api.manager.fallback;

import lombok.extern.slf4j.Slf4j;
import org.industry.api.manager.feign.DictionaryClient;
import org.industry.common.constant.ServiceConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(value = ServiceConstant.FEIGN_FALLBACK_SWITCH, havingValue = "true")
public class DictionaryClientFallback implements FallbackFactory<DictionaryClient> {

    @Override
    public DictionaryClient create(Throwable cause) {
        // 错误原因日志
        String message = cause.getMessage() == null ? "No available server for client: " + ServiceConstant.Manager.SERVICE_NAME : cause.getMessage();
        log.error("Fallback: {}", message);
        return new DictionaryClient() {
        };
    }
}

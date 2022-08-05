package org.industry.api.auth.fallback;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.feign.UserClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@ConditionalOnProperty(value = ServiceConstant.FEIGN_FALLBACK_SWITCH, havingValue = "true")
public class UserFallback implements FallbackFactory<UserClient> {

    @Override
    public UserClient create(Throwable cause) {
        String message = cause.getMessage() == null ? "No available server for client: " + ServiceConstant.Auth.SERVICE_NAME : cause.getMessage();
        log.error("Fallback: {}", message);
        return new UserClient() {
        };
    }
}

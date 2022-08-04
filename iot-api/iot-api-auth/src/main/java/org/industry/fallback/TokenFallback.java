package org.industry.fallback;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.feign.TokenClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(value = ServiceConstant.FEIGN_FALLBACK_SWITCH, havingValue = "true")
public class TokenFallback implements FallbackFactory<TokenClient> {
    @Override
    public TokenClient create(Throwable cause) {
        String message = cause.getMessage() == null ? "No available server for client: " + ServiceConstant.Auth.SERVICE_NAME : cause.getMessage();
        log.error("Fallback: {}", message);
        return new TokenClient() {
            @Override
            public R<Long> checkTokenValid(Login login) {
                return R.fail(message);
            }
        };
    }
}

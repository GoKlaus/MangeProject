package org.industry.api.auth.fallback;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.feign.TokenClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenFallback implements FallbackFactory<TokenClient> {
    @Override
    public TokenClient create(Throwable cause) {
        String message = cause.getMessage() == null ? "No available server for client: " + ServiceConstant.Auth.SERVICE_NAME : cause.getMessage();
        log.error("Fallback: {}", message);
        return new TokenClient() {
            @Override
            public R<String> generateSalt(Login login) {
                return R.fail(message);
            }

            @Override
            public R<String> generateToken(Login login) {
                return R.fail(message);
            }

            @Override
            public R<Long> checkTokenValid(Login login) {
                return R.fail(message);
            }

            @Override
            public R<Boolean> cancelToken(Login login) {
                return R.fail(message);
            }
        };
    }
}

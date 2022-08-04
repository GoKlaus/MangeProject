package org.industry.fallback;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.feign.BlackIpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(value = ServiceConstant.FEIGN_FALLBACK_SWITCH, havingValue = "true")
public class BlackIpFallback implements FallbackFactory<BlackIpClient> {
    @Override
    public BlackIpClient create(Throwable cause) {
        String message = cause.getMessage() == null ? "No available server for client: " + ServiceConstant.Auth.SERVICE_NAME : cause.getMessage();
        log.error("FallbackL: {}", message);
        return new BlackIpClient() {

            @Override
            public R<Boolean> checkBlackIpValid(String remoteIp) {
                return R.fail(message);
            }
        };
    }

}

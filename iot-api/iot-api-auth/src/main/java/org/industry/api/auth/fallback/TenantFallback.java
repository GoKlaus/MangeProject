package org.industry.api.auth.fallback;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.model.Tenant;
import org.industry.api.auth.feign.TenantClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@ConditionalOnProperty(value = ServiceConstant.FEIGN_FALLBACK_SWITCH, havingValue = "true")
public class TenantFallback implements FallbackFactory<TenantClient> {
    @Override
    public TenantClient create(Throwable cause) {
        String message = cause.getMessage() == null ? "No available server for client: " + ServiceConstant.Auth.SERVICE_NAME : cause.getMessage();
        log.error("Fallback: {}", message);
        return new TenantClient() {
            @Override
            public R<Tenant> selectByName(String name) {
                return R.fail(message);
            }
        };
    }
}

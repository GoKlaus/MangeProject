package org.industry.feign;

import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.fallback.TenantFallback;
import org.industry.fallback.TokenFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(path = ServiceConstant.Auth.TOKEN_URL_PREFIX, name = ServiceConstant.Auth.SERVICE_NAME, fallbackFactory = TokenFallback.class)
public interface TokenClient {
    R<Long> checkTokenValid(Login login);
}

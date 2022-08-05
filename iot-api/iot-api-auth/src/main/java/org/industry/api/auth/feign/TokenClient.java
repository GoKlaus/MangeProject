package org.industry.api.auth.feign;

import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.fallback.TokenFallback;
import org.industry.common.valid.Check;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(path = ServiceConstant.Auth.TOKEN_URL_PREFIX, name = ServiceConstant.Auth.SERVICE_NAME, fallbackFactory = TokenFallback.class)
public interface TokenClient {

    /**
     * 校验token是否有效
     *
     * @param login
     * @return
     */
    @PostMapping("/check")
    R<Long> checkTokenValid(@Validated(Check.class) @RequestBody Login login);
}

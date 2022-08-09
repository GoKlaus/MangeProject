package org.industry.api.auth.feign;

import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.fallback.TokenFallback;
import org.industry.common.valid.Auth;
import org.industry.common.valid.Check;
import org.industry.common.valid.Update;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(path = ServiceConstant.Auth.TOKEN_URL_PREFIX, name = ServiceConstant.Auth.SERVICE_NAME, fallbackFactory = TokenFallback.class)
public interface TokenClient {

    /**
     * 生成用户随机 Salt
     *
     * @param login Login
     * @return R<String>
     */
    @PostMapping("/salt")
    R<String> generateSalt(@Validated(Update.class) @RequestBody Login login);

    /**
     * 生成用户 Token 令牌
     *
     * @param login Login
     * @return R<String>
     */
    @PostMapping("/generate")
    R<String> generateToken(@Validated(Auth.class) @RequestBody Login login);

    /**
     * 检测用户 Token 令牌是否有效
     *
     * @param login Login
     * @return R<Boolean>
     */
    @PostMapping("/check")
    R<Long> checkTokenValid(@Validated(Check.class) @RequestBody Login login);

    /**
     * 注销用户的Token令牌
     *
     * @param login Login
     * @return R<Boolean>
     */
    @PostMapping("/cancel")
    R<Boolean> cancelToken(@Validated(Update.class) @RequestBody Login login);
}

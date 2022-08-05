package org.industry.api.auth.feign;

import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.fallback.BlackIpFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;

@FeignClient(path = ServiceConstant.Auth.BLACK_IP_URL_PREFIX, name = ServiceConstant.Auth.SERVICE_NAME, fallbackFactory = BlackIpFallback.class)
public interface BlackIpClient {
    /**
     * 检查黑名单
     *
     * @param remoteIp
     * @return
     */
    @GetMapping("/check/{ip}")
    R<Boolean> checkBlackIpValid(@NotNull@PathVariable("ip") String remoteIp);

}

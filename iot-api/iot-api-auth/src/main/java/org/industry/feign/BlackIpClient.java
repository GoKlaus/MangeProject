package org.industry.feign;

import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.fallback.BlackIpFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(path = ServiceConstant.Auth.BLACK_IP_URL_PREFIX, name = ServiceConstant.Auth.SERVICE_NAME, fallbackFactory = BlackIpFallback.class)
public interface BlackIpClient {
    /**
     * 检查黑名单
     *
     * @param remoteIp
     * @return
     */
    R<Boolean> checkBlackIpValid(String remoteIp);

}

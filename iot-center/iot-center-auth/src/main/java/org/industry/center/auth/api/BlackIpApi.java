package org.industry.center.auth.api;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.feign.BlackIpClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ServiceConstant.Auth.BLACK_IP_URL_PREFIX)
public class BlackIpApi implements BlackIpClient {
    @Override
    public R<Boolean> checkBlackIpValid(String remoteIp) {
        return null;
    }
}

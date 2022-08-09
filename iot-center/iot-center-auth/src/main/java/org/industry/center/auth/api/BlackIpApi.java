package org.industry.center.auth.api;

import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.service.BlackIpService;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.feign.BlackIpClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(ServiceConstant.Auth.BLACK_IP_URL_PREFIX)
public class BlackIpApi implements BlackIpClient {

    @Resource
    private BlackIpService blackIpService;

    @Override
    public R<Boolean> checkBlackIpValid(String ip) {
        try {
            return blackIpService.checkBlackIpValid(ip) ? R.ok() : R.fail();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }
}

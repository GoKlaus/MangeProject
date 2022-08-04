package org.industry.center.auth.api;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.feign.TokenClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ServiceConstant.Auth.TOKEN_URL_PREFIX)
public class TokenApi implements TokenClient {
    @Override
    public R<Long> checkTokenValid(Login login) {
        return null;
    }
}

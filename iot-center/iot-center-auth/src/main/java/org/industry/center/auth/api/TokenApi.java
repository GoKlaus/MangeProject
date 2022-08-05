package org.industry.center.auth.api;

import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.bean.TokenValid;
import org.industry.center.auth.service.TokenService;
import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.feign.TokenClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(ServiceConstant.Auth.TOKEN_URL_PREFIX)
public class TokenApi implements TokenClient {
    @Resource
    private TokenService tokenService;
    @Override
    public R<Long> checkTokenValid(Login login) {
       TokenValid tokenValid = tokenService.checkTokenValid(login.getName(), login.getSalt(), login.getToken());
        return null;
    }
}

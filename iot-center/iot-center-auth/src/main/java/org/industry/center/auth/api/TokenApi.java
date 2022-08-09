package org.industry.center.auth.api;

import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.bean.TokenValid;
import org.industry.center.auth.service.TokenService;
import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.feign.TokenClient;
import org.industry.common.exception.UnAuthorizedException;
import org.industry.common.utils.IotUtil;
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
        if (tokenValid.isValid()) {
            return R.ok(tokenValid.getExpireTime().getTime(), "The token will expire in " + IotUtil.formatCompleteData(tokenValid.getExpireTime()));
        }
        throw new UnAuthorizedException("Token invalid");
    }

    /**
     * 生成用户随机 Salt
     *
     * @param login Login
     * @return R<String>
     */
    @Override
    public R<String> generateSalt(Login login) {
        return null;
    }

    /**
     * 生成用户 Token 令牌
     *
     * @param login Login
     * @return R<String>
     */
    @Override
    public R<String> generateToken(Login login) {
        return null;
    }

    /**
     * 注销用户的Token令牌
     *
     * @param login Login
     * @return R<Boolean>
     */
    @Override
    public R<Boolean> cancelToken(Login login) {
        return null;
    }
}

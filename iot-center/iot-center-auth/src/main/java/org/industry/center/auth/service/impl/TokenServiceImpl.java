package org.industry.center.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.bean.TokenValid;
import org.industry.center.auth.service.TokenService;
import org.industry.common.constant.CacheConstant;
import org.industry.common.constant.CommonConstant;
import org.industry.common.utils.KeyUtil;
import org.industry.core.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public TokenValid checkTokenValid(String name, String salt, String token) {
        String redisToken = redisUtil.getKey(CacheConstant.Entity.USER + CacheConstant.Suffix.TOKEN + CommonConstant.Symbol.SEPARATOR + name, String.class);
        if (StrUtil.isBlank(redisToken) || !redisToken.equals(token)) {
            return new TokenValid(false, null);
        }
        try {
            Claims claims = KeyUtil.parserToken(name, salt, token);
            return new TokenValid(true, claims.getExpiration());
        } catch (Exception e) {
            return new TokenValid(false, null);
        }
    }
}

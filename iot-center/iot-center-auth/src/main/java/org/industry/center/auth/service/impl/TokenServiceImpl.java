package org.industry.center.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
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
import java.util.concurrent.TimeUnit;

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

    /**
     * 生成用户的随机 salt
     *
     * @param username Username
     * @return String
     */
    @Override
    public String generateSalt(String username) {
        String redisSaltKey = CacheConstant.Entity.USER + CacheConstant.Suffix.SALT + CommonConstant.Symbol.SEPARATOR + username;
        String salt = redisUtil.getKey(redisSaltKey, String.class);
        if (StrUtil.isBlank(salt)) {
            salt = RandomUtil.randomString(16);
            redisUtil.setKey(redisSaltKey, salt, CacheConstant.Timeout.SALT_CACHE_TIMEOUT, TimeUnit.MINUTES);
        }
        return salt;
    }

    /**
     * 生成用户的Token令牌
     *
     * @param tenant   Tenant
     * @param name     User Name
     * @param salt     User Salt
     * @param password User Password
     * @return String
     */
    @Override
    public String generateToken(String tenant, String name, String salt, String password) {
        return null;
    }

    /**
     * 注销用户的Token令牌
     *
     * @param username Username
     */
    @Override
    public boolean cancelToken(String username) {
        return false;
    }
}

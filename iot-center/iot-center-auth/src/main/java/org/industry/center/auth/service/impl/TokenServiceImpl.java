package org.industry.center.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.bean.TokenValid;
import org.industry.center.auth.bean.UserLimit;
import org.industry.center.auth.service.TenantBindService;
import org.industry.center.auth.service.TenantService;
import org.industry.center.auth.service.TokenService;
import org.industry.center.auth.service.UserService;
import org.industry.common.constant.CacheConstant;
import org.industry.common.constant.CommonConstant;
import org.industry.common.exception.ServiceException;
import org.industry.common.model.Tenant;
import org.industry.common.model.User;
import org.industry.common.utils.IotUtil;
import org.industry.common.utils.KeyUtil;
import org.industry.core.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserService userService;

    @Resource
    private TenantBindService tenantBindService;
    @Resource
    private TenantService tenantService;

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
        checkUserLimit(name);
        Tenant tempTenant = tenantService.selectByName(tenant);
        User tempUser = userService.selectByName(name, false);
        if (tempTenant.getEnable() && tempUser.getEnable()) {
            tenantBindService.selectByTenantIdAndUserId(tempTenant.getId(), tempUser.getId());
            String redisSaltKey = CacheConstant.Entity.USER + CacheConstant.Suffix.SALT + CommonConstant.Symbol.SEPARATOR + name;
            String tempSalt = redisUtil.getKey(redisSaltKey, String.class);
            if (StrUtil.isNotBlank(tempSalt) && tempSalt.equals(salt)) {
                if (IotUtil.md5(tempUser.getPassword() + tempSalt).equals(password)) {
                    String redisTokenKey = CacheConstant.Entity.USER + CacheConstant.Suffix.TOKEN + CommonConstant.Symbol.SEPARATOR + name;
                    String token = KeyUtil.generateToken(name, tempSalt);
                    redisUtil.setKey(redisTokenKey, token, CacheConstant.Timeout.TOKEN_CACHE_TIMEOUT, TimeUnit.HOURS);
                    return token;
                }
            }
        }
        updateUserLimit(name, true);
        throw new ServiceException("Invalid tenant、username、password");
    }

    /**
     * 注销用户的Token令牌
     *
     * @param username Username
     */
    @Override
    public boolean cancelToken(String username) {
        redisUtil.deleteKey(CacheConstant.Entity.USER + CacheConstant.Suffix.TOKEN + CommonConstant.Symbol.SEPARATOR + username);
        return true;
    }

    /**
     * 检测用户登录限制，返回该用户是否受限
     *
     * @param username Username
     */
    private void checkUserLimit(String username) {
        String redisKey = CacheConstant.Entity.USER + CacheConstant.Suffix.LIMIT + CommonConstant.Symbol.SEPARATOR + username;
        UserLimit limit = redisUtil.getKey(redisKey, UserLimit.class);
        if (null != limit && limit.getTimes() >= 5) {
            Date now = new Date();
            long interval = limit.getExpireTime().getTime() - now.getTime();
            if (interval > 0) {
                limit = updateUserLimit(username, false);
                throw new ServiceException("Access restricted，Please try again after {}", IotUtil.formatCompleteData(limit.getExpireTime()));
            }
        }
    }

    /**
     * 更新用户登录限制
     *
     * @param username Username
     * @return UserLimit
     */
    private UserLimit updateUserLimit(String username, boolean expireTime) {
        int amount = CacheConstant.Timeout.USER_LIMIT_TIMEOUT;
        String redisKey = CacheConstant.Entity.USER + CacheConstant.Suffix.LIMIT + CommonConstant.Symbol.SEPARATOR + username;
        UserLimit limit = Optional.ofNullable(redisUtil.getKey(redisKey, UserLimit.class)).orElse(new UserLimit(0, new Date()));
        limit.setTimes(limit.getTimes() + 1);
        if (limit.getTimes() > 20) {
            //TODO 拉黑IP和锁定用户操作，然后通过Gateway进行拦截
            amount = 24 * 60;
        } else if (limit.getTimes() > 5) {
            amount = limit.getTimes() * CacheConstant.Timeout.USER_LIMIT_TIMEOUT;
        }
        if (expireTime) {
            limit.setExpireTime(IotUtil.expireTime(amount, Calendar.MINUTE));
        }
        redisUtil.setKey(redisKey, limit, 1, TimeUnit.DAYS);
        return limit;
    }
}

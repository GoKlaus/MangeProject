package org.industry.center.auth.service;

import org.industry.center.auth.bean.TokenValid;

public interface TokenService {
    /**
     * 生成用户的随机 salt
     *
     * @param username Username
     * @return String
     */
    String generateSalt(String username);

    /**
     * 生成用户的Token令牌
     *
     * @param tenant   Tenant
     * @param name     User Name
     * @param salt     User Salt
     * @param password User Password
     * @return String
     */
    String generateToken(String tenant, String name, String salt, String password);

    /**
     * 校验用户的Token令牌是否有效
     *
     * @param username Username
     * @param token    Token
     * @return TokenValid
     */
    TokenValid checkTokenValid(String username, String salt, String token);

    /**
     * 注销用户的Token令牌
     *
     * @param username Username
     */
    boolean cancelToken(String username);
}

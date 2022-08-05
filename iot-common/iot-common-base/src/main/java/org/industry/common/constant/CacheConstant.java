package org.industry.common.constant;

public interface CacheConstant {

    interface Timeout {
        /**
         * salt 在 redis 中的失效时间，分钟
         */
        int SALT_CACHE_TIMEOUT = 5;
        /**
         * user 登陆限制失效时间，分钟
         */
        int USER_LIMIT_TIMEOUT = 5;
        /**
         * token 在 redis 中的失效时间，小时
         */
        int TOKEN_CACHE_TIMEOUT = 12;

    }

    interface Entity {
        String USER = "user";
    }

    interface Suffix {
        String TOKEN = "_token";
    }
}

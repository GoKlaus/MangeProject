package org.industry.common.constant;

/**
 * 服务常量
 */
public interface ServiceConstant {

    /**
     * 搭配{@link org.springframework.boot.autoconfigure.condition.ConditionalOnProperty}条件使用
     * 用来控制api包中的{@link org.springframework.cloud.openfeign.FallbackFactory}的实例
     * 是否实例化托管到容器中，这么做的意义在于服务提供不用实例化这部分不需要的Bean
     */
    String FEIGN_FALLBACK_SWITCH = "feign.fallback.switch";

    interface Header {
        String X_AUTH_USER_ID = "X-Auth-User-Id";
        String X_AUTH_TOKEN = "X-Auth-Token";
        String X_AUTH_TENANT_ID = "X-Auth-Tenant-Id";
        String X_AUTH_TENANT = "X-Auth-Tenant";
        String X_AUTH_USER = "X-Auth-User";
    }

    interface Auth {

        String SERVICE_NAME = "IOT-CENTER-AUTH";
        String USER_URL_PREFIX = "/auth/user";
        String BLACK_IP_URL_PREFIX = "/auth/black_ip";

        String TENANT_URL_PREFIX = "/auth/tenant";
        String TOKEN_URL_PREFIX = "/auth/token";
    }

    /**
     * manager 模块常量
     */
    interface Manager {

        String DICTIONARY_URL_PREFIX = "/manager/dictionary";

        String SERVICE_NAME = "IOT-CENTER-MANAGER";
    }
}

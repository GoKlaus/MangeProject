package org.industry.center.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * 开启事务管理
 * 开启缓存
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching(mode = AdviceMode.ASPECTJ)
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
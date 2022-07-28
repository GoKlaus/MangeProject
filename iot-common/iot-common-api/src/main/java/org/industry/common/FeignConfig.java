package org.industry.common;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * open feign 配置
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }
}

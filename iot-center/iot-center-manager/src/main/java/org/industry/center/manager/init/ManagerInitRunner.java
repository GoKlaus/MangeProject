package org.industry.center.manager.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 指定加载哪个模块
 */
@Component
@EnableFeignClients(basePackages = {
        "org.industry.api.auth.*"
})
@ComponentScan(basePackages = {
        "org.industry.api.auth"
})
public class ManagerInitRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}

package org.industry.gateway.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@EnableFeignClients(basePackages = {"org.industry.api.auth.*"})
@ComponentScan(basePackages = {"org.industry.api.auth.*"})
public class GatewayInitRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}

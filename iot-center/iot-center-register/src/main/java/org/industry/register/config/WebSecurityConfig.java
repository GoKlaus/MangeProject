package org.industry.register.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * eureka注册中心开启安全认证，需要配置csrf关闭，否则client无法访问注册中心
     *
     * @param web the {@link HttpSecurity} to modify
     * @throws Exception
     */
    @Override
    @SneakyThrows
    public void configure(HttpSecurity web) throws Exception {
        try {
            web.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/actuator/**").permitAll()
                    .anyRequest()
                    .authenticated().and().httpBasic();
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
        }
    }

}

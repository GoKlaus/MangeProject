package org.industry.center.auth.api;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.feign.UserClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ServiceConstant.Auth.USER_URL_PREFIX)
public class UserApi implements UserClient {
}

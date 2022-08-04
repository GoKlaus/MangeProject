package org.industry.center.auth.api;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.model.Tenant;
import org.industry.feign.TenantClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ServiceConstant.Auth.TENANT_URL_PREFIX)
public class TenantApi implements TenantClient {
    @Override
    public R<Tenant> selectByName(Login login) {
        return null;
    }
}

package org.industry.center.auth.api;

import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.service.TenantService;
import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.model.Tenant;
import org.industry.api.auth.feign.TenantClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(ServiceConstant.Auth.TENANT_URL_PREFIX)
public class TenantApi implements TenantClient {

    @Resource
    private TenantService tenantService;

    @Override
    public R<Tenant> selectByName(String name) {
        try {
            Tenant tenant = tenantService.selectByName(name);
            if (null != tenant) {
                return R.ok(tenant);
            }
        } catch (Exception e) {
            R.fail(e.getMessage());
        }
        return R.fail("Resource does not exist");
    }
}

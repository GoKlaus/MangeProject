package org.industry.feign;

import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.model.Tenant;
import org.industry.fallback.TenantFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(path = ServiceConstant.Auth.TENANT_URL_PREFIX, name = ServiceConstant.Auth.SERVICE_NAME,fallbackFactory = TenantFallback.class)
public interface TenantClient {
    R<Tenant> selectByName(Login login);
}

package org.industry.api.auth.feign;

import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.model.Tenant;
import org.industry.api.auth.fallback.TenantFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;

@FeignClient(path = ServiceConstant.Auth.TENANT_URL_PREFIX, name = ServiceConstant.Auth.SERVICE_NAME, fallbackFactory = TenantFallback.class)
public interface TenantClient {

    /**
     * 根据 Name 查询Tenant
     * @param login
     * @return
     */
    @GetMapping("/name/{name}")
    R<Tenant> selectByName(@NotNull @PathVariable(value = "name") String login);
}

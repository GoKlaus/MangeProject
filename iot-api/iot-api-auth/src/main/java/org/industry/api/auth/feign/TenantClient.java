package org.industry.api.auth.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.TenantDto;
import org.industry.common.model.Tenant;
import org.industry.api.auth.fallback.TenantFallback;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

@FeignClient(path = ServiceConstant.Auth.TENANT_URL_PREFIX, name = ServiceConstant.Auth.SERVICE_NAME, fallbackFactory = TenantFallback.class)
public interface TenantClient {
    @PostMapping("/add")
    R<Tenant> add(@Validated(Insert.class) @RequestBody Tenant tenant);

    @PostMapping("/update")
    R<Tenant> update(@Validated(Update.class) @RequestBody Tenant tenant);

    @GetMapping("/delete/{id}")
    R<Boolean> delete(@NotNull @PathVariable("id") String id);

    @GetMapping("/id/{id}")
    R<Tenant> selectById(@NotNull @PathVariable("id")String id);

    @PostMapping("/list")
    R<Page<Tenant>> list( @RequestBody(required = false) TenantDto tenantDto);

    /**
     * 根据 Name 查询Tenant
     *
     * @param login
     * @return
     */
    @GetMapping("/name/{name}")
    R<Tenant> selectByName(@NotNull @PathVariable(value = "name") String login);
}

package org.industry.center.auth.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.service.TenantService;
import org.industry.common.bean.Login;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.TenantDto;
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

    /**
     * @param tenant
     * @return
     */
    @Override
    public R<Tenant> add(Tenant tenant) {
        try {
            Tenant add = tenantService.add(tenant);
            if (null != add) {
                return R.ok(add);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * @param tenant
     * @return
     */
    @Override
    public R<Tenant> update(Tenant tenant) {
        try {
            Tenant update = tenantService.update(tenant);
            if (null != update) {
                return R.ok(update);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public R<Boolean> delete(String id) {
        try {
            return tenantService.delete(id) ? R.ok() : R.fail();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public R<Tenant> selectById(String id) {
        return null;
    }

    /**
     * @param tenantDto
     * @return
     */
    @Override
    public R<Page<Tenant>> list(TenantDto tenantDto) {
        try {
            Page<Tenant> page = tenantService.list(tenantDto);
            if (null != page) {
                return R.ok(page);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }
}

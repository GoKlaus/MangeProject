package org.industry.center.auth.service;

import org.industry.common.model.Tenant;

public interface TenantService {
    /**
     * 根据租户名查询租户信息
     *
     * @param name
     * @return
     */
    Tenant selectByName(String name);
}

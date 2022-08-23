
package org.industry.center.auth.service;


import org.industry.common.base.Service;
import org.industry.common.dto.TenantBindDto;
import org.industry.common.model.TenantBind;

/**
 * TenantBind Interface
 *
 */
public interface TenantBindService extends Service<TenantBind, TenantBindDto> {

    /**
     * 根据 租户ID 和 关联的用户ID 查询
     *
     * @param tenantId TenantID
     * @param userId   userId
     * @return TenantBind
     */
    TenantBind selectByTenantIdAndUserId(String tenantId, String userId);
}

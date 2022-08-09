package org.industry.common.dto;

import lombok.*;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.Tenant;
import org.springframework.beans.BeanUtils;

/**
 * User DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantDto extends Tenant implements Converter<Tenant, TenantDto> {

    private Pages page;

    @Override
    public void convertDtoToDo(Tenant tenant) {
        BeanUtils.copyProperties(this, tenant);
    }

    @Override
    public void convertDoToDto(Tenant tenant) {
        BeanUtils.copyProperties(tenant, this);
    }
}
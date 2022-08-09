package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.TenantBind;
import org.springframework.beans.BeanUtils;

/**
 * TenantBind DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantBindDto extends TenantBind implements Converter<TenantBind, TenantBindDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(TenantBind bind) {
        BeanUtils.copyProperties(this, bind);
    }

    @Override
    public void convertDoToDto(TenantBind bind) {
        BeanUtils.copyProperties(bind, this);
    }
}
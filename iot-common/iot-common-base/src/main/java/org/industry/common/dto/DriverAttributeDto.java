package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.DriverAttribute;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DriverAttributeDto extends DriverAttribute implements Converter<DriverAttribute, DriverAttributeDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(DriverAttribute info) {
        BeanUtils.copyProperties(this, info);
    }

    @Override
    public void convertDoToDto(DriverAttribute info) {
        BeanUtils.copyProperties(info, this);
    }
}
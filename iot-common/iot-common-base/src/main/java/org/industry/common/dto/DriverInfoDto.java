package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.DriverInfo;
import org.springframework.beans.BeanUtils;

/**
 * DriverInfo DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DriverInfoDto extends DriverInfo implements Converter<DriverInfo, DriverInfoDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(DriverInfo info) {
        BeanUtils.copyProperties(this, info);
    }

    @Override
    public void convertDoToDto(DriverInfo info) {
        BeanUtils.copyProperties(info, this);
    }
}
package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.Driver;
import org.springframework.beans.BeanUtils;

/**
 * Driver DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DriverDto extends Driver implements Converter<Driver, DriverDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(Driver driver) {
        BeanUtils.copyProperties(this, driver);
    }

    @Override
    public void convertDoToDto(Driver driver) {
        BeanUtils.copyProperties(driver, this);
    }
}
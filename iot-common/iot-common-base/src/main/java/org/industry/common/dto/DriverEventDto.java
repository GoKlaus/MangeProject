package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.DriverEvent;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class DriverEventDto implements Serializable, Converter<DriverEvent, DriverEventDto> {
    private static final long serialVersionUID = 1L;

    private String serviceName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(DriverEvent driverEvent) {
        BeanUtils.copyProperties(this, driverEvent);
    }

    @Override
    public void convertDoToDto(DriverEvent driverEvent) {
        BeanUtils.copyProperties(driverEvent, this);
    }
}

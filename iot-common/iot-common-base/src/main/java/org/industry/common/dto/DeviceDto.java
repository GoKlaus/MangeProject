
package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.Device;
import org.springframework.beans.BeanUtils;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DeviceDto extends Device implements Converter<Device, DeviceDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(Device device) {
        BeanUtils.copyProperties(this, device);
    }

    @Override
    public void convertDoToDto(Device device) {
        BeanUtils.copyProperties(device, this);
    }
}
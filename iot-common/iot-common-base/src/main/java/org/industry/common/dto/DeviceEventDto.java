package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.DeviceEvent;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class DeviceEventDto implements Serializable, Converter<DeviceEvent, DeviceEventDto> {
    private static final long serialVersionUID = 1L;

    private String deviceId;
    private String pointId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    public void convertDtoToDo(DeviceEvent deviceEvent) {
        BeanUtils.copyProperties(this, deviceEvent);
    }

    public void convertDoToDto(DeviceEvent deviceEvent) {
        BeanUtils.copyProperties(deviceEvent, this);
    }
}

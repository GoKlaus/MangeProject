package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.Rtmp;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * Rtmp DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RtmpDto extends Rtmp implements Serializable, Converter<Rtmp, RtmpDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    public RtmpDto(boolean autoStart) {
        super.setAutoStart(autoStart);
    }

    @Override
    public void convertDtoToDo(Rtmp rtmp) {
        BeanUtils.copyProperties(this, rtmp);
    }

    @Override
    public void convertDoToDto(Rtmp rtmp) {
        BeanUtils.copyProperties(rtmp, this);
    }
}
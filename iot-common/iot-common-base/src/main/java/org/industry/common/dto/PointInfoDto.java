package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.PointInfo;
import org.springframework.beans.BeanUtils;

/**
 * PointInfo DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PointInfoDto extends PointInfo implements Converter<PointInfo, PointInfoDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(PointInfo info) {
        BeanUtils.copyProperties(this, info);
    }

    @Override
    public void convertDoToDto(PointInfo info) {
        BeanUtils.copyProperties(info, this);
    }
}
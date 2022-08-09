package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.PointAttribute;
import org.springframework.beans.BeanUtils;

/**
 * PointAttribute DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PointAttributeDto extends PointAttribute implements Converter<PointAttribute, PointAttributeDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(PointAttribute info) {
        BeanUtils.copyProperties(this, info);
    }

    @Override
    public void convertDoToDto(PointAttribute info) {
        BeanUtils.copyProperties(info, this);
    }
}
package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.Point;
import org.springframework.beans.BeanUtils;

/**
 * Point DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PointDto extends Point implements Converter<Point, PointDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(Point point) {
        BeanUtils.copyProperties(this, point);
    }

    @Override
    public void convertDoToDto(Point point) {
        BeanUtils.copyProperties(point, this);
    }
}
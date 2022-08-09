package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.Label;
import org.springframework.beans.BeanUtils;

/**
 * Label DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LabelDto extends Label implements Converter<Label, LabelDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(Label label) {
        BeanUtils.copyProperties(this, label);
    }

    @Override
    public void convertDoToDto(Label label) {
        BeanUtils.copyProperties(label, this);
    }
}
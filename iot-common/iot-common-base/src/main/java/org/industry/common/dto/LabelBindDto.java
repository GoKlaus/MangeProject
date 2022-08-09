package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.LabelBind;
import org.springframework.beans.BeanUtils;

/**
 * LabelBind DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LabelBindDto extends LabelBind implements Converter<LabelBind, LabelBindDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(LabelBind bind) {
        BeanUtils.copyProperties(this, bind);
    }

    @Override
    public void convertDoToDto(LabelBind bind) {
        BeanUtils.copyProperties(bind, this);
    }
}
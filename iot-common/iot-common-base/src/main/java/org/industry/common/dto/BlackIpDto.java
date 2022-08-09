package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.BlackIp;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BlackIpDto extends BlackIp implements Converter<BlackIp, BlackIpDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(BlackIp blackIp) {
        BeanUtils.copyProperties(this, blackIp);
    }

    @Override
    public void convertDoToDto(BlackIp blackIp) {
        BeanUtils.copyProperties(blackIp, this);
    }
}
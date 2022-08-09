package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.ProfileBind;
import org.springframework.beans.BeanUtils;

/**
 * ProfileBind DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProfileBindDto extends ProfileBind implements Converter<ProfileBind, ProfileBindDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(ProfileBind bind) {
        BeanUtils.copyProperties(this, bind);
    }

    @Override
    public void convertDoToDto(ProfileBind bind) {
        BeanUtils.copyProperties(bind, this);
    }
}
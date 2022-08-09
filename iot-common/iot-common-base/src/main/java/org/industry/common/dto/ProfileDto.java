package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.Profile;
import org.springframework.beans.BeanUtils;

/**
 * Profile DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProfileDto extends Profile implements Converter<Profile, ProfileDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(Profile profile) {
        BeanUtils.copyProperties(this, profile);
    }

    @Override
    public void convertDoToDto(Profile profile) {
        BeanUtils.copyProperties(profile, this);
    }
}
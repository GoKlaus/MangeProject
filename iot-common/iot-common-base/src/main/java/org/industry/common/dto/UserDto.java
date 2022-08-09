package org.industry.common.dto;

import lombok.*;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.User;
import org.springframework.beans.BeanUtils;

/**
 * User DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDto extends User implements Converter<User, UserDto> {

    private Pages page;

    @Override
    public void convertDtoToDo(User user) {
        BeanUtils.copyProperties(this, user);
    }

    @Override
    public void convertDoToDto(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
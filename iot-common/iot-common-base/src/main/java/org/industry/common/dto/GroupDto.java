package org.industry.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.industry.common.base.Converter;
import org.industry.common.bean.Pages;
import org.industry.common.model.Group;
import org.springframework.beans.BeanUtils;

/**
 * Group DTO
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GroupDto extends Group implements Converter<Group, GroupDto> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pages page;

    @Override
    public void convertDtoToDo(Group group) {
        BeanUtils.copyProperties(this, group);
    }

    @Override
    public void convertDoToDto(Group group) {
        BeanUtils.copyProperties(group, this);
    }
}
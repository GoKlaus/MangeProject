package org.industry.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 设备表
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Group extends Description {

    @NotBlank(message = "name can't be empty", groups = {Insert.class})
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$", message = "Invalid name,contains invalid characters or length is not in the range of 2~32", groups = {Insert.class, Update.class})
    private String name;

    private String groupId;

    /**
     * group 排序位置
     */
    private Integer position;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String tenantId;

    public Group(String name, String tenantId) {
        this.name = name;
        this.tenantId = tenantId;
    }
}

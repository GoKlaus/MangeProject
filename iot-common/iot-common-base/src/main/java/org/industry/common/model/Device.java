package org.industry.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Device extends Description {

    @NotBlank(message = "name can't be empty", groups = {Insert.class})
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$", message = "Invalid name,contains invalid characters or length is not in the range of 2~32", groups = {Insert.class, Update.class})
    private String name;

    /**
     * 是否结构化存储数据
     * 默认：false，为单点存储
     */
    private Boolean multi;

    private Boolean enable;

    @TableField(exist = false)
    private Set<String> profileIds = new HashSet<>(8);

    @NotNull(message = "driver id can't be empty", groups = {Insert.class, Update.class})
    private String driverId;

    // TODO 后期再实现分组，先放着占个坑 @NotNull(message = "group id can't be empty", groups = {Insert.class, Update.class})
    private String groupId;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String tenantId;

    public Device(String name, String profileId, String groupId) {
        super();
        this.name = name;
        //this.profileId = profileId;
        this.groupId = groupId;
    }
}

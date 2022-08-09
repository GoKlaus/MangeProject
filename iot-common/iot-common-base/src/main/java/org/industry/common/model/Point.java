package org.industry.common.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.constant.ValueConstant;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 设备变量表
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Point extends Description {

    @NotBlank(message = "name can't be empty", groups = {Insert.class})
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$", message = "Invalid name,contains invalid characters or length is not in the range of 2~32", groups = {Insert.class, Update.class})
    private String name;

    private String type;
    private Short rw;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Float base;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Float minimum;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Float maximum;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Float multiple;

    private Boolean accrue;
    private String format;
    private String unit;

    private Boolean enable;

    @NotNull(message = "profile id can't be empty", groups = {Insert.class, Update.class})
    private String profileId;

    // TODO 后期再实现分组，先放着占个坑 @NotNull(message = "group id can't be empty", groups = {Insert.class, Update.class})
    private String groupId;

    private String tenantId;

    public Point(String name, String type, Short rw, Float base, Float minimum, Float maximum, Float multiple,
                 Boolean accrue, String format, String unit, String profileId, String tenantId) {
        this.name = name;
        this.type = type;
        this.rw = rw;
        this.base = base;
        this.minimum = minimum;
        this.maximum = maximum;
        this.multiple = multiple;
        this.accrue = accrue;
        this.format = format;
        this.unit = unit;
        this.profileId = profileId;
        this.tenantId = tenantId;
    }

    public Point setDefault() {
        this.type = ValueConstant.Type.STRING;
        this.rw = 0;
        this.base = 0F;
        this.minimum = -999999F;
        this.maximum = 999999F;
        this.multiple = 1F;
        this.accrue = false;
        this.format = "%3.f";
        this.unit = "\"";
        return this;
    }
}

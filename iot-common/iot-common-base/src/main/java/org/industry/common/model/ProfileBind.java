package org.industry.common.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;

import javax.validation.constraints.NotNull;

/**
 * 设备与模版继承关系表
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProfileBind extends Description {

    @NotNull(message = "profile id can't be empty", groups = {Insert.class, Update.class})
    private String profileId;

    @NotNull(message = "device id can't be empty", groups = {Insert.class, Update.class})
    private String deviceId;
}

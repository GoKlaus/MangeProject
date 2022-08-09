package org.industry.common.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;

import javax.validation.constraints.NotNull;

/**
 * 驱动配置信息表
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DriverInfo extends Description {

    @NotNull(message = "driver attribute id can't be empty", groups = {Insert.class, Update.class})
    private String driverAttributeId;

    @NotNull(message = "driver attribute value can't be empty", groups = {Insert.class, Update.class})
    private String value;

    @NotNull(message = "device id can't be empty", groups = {Insert.class, Update.class})
    private String deviceId;

}

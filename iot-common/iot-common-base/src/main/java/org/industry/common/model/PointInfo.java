package org.industry.common.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;

import javax.validation.constraints.NotNull;

/**
 * 位号配置信息表
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PointInfo extends Description {

    @NotNull(message = "point attribute id can't be empty", groups = {Insert.class, Update.class})
    private String pointAttributeId;

    @NotNull(message = "point attribute value can't be empty", groups = {Insert.class, Update.class})
    private String value;

    @NotNull(message = "device id can't be empty", groups = {Insert.class, Update.class})
    private String deviceId;

    @NotNull(message = "point id can't be empty", groups = {Insert.class, Update.class})
    private String pointId;
}

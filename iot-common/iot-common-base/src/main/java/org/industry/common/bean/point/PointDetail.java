package org.industry.common.bean.point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 设备位号详情
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class PointDetail {

    @NotBlank(message = "device name can't be empty", groups = {Insert.class})
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$", message = "Invalid device name,contains invalid characters or length is not in the range of 2~32", groups = {Insert.class, Update.class})
    private String deviceName;

    @NotBlank(message = "point name can't be empty", groups = {Insert.class})
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5][A-Za-z0-9\\u4e00-\\u9fa5-_#@/\\.\\|]{1,31}$", message = "Invalid point name,contains invalid characters or length is not in the range of 2~32", groups = {Insert.class, Update.class})
    private String pointName;

    @NotNull(message = "driver id can't be empty", groups = {Insert.class, Update.class})
    private String driverId;

    private String deviceId;
    private String pointId;

    public PointDetail(String deviceId, String pointId) {
        this.deviceId = deviceId;
        this.pointId = pointId;
    }
}

package org.industry.common.sdk.bean.driver;

import lombok.Getter;
import lombok.Setter;
import org.industry.common.constant.CommonConstant;
import org.industry.common.model.DriverAttribute;
import org.industry.common.model.PointAttribute;
import org.industry.common.sdk.bean.schedule.ScheduleProperty;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated({Insert.class, Update.class})
@ConfigurationProperties(prefix = "driver")
public class DriverProperty {

    // 租户
    private String tenant;
    //
    private String name;
    //
    private String type = CommonConstant.Driver.Type.DRIVER;
    //
    private String description;
    //
    private ScheduleProperty schedule;
    //
    private List<DriverAttribute> driverAttributes;
    //
    private List<PointAttribute> pointAttributes;
}

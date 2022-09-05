package org.industry.common.sdk.bean.schedule;

import lombok.*;

@Setter
@Getter
@Data
@ToString(callSuper = true)
public class ScheduleProperty {
    private ScheduleConfig read;
    private ScheduleConfig custom;
    private ScheduleConfig status;
}

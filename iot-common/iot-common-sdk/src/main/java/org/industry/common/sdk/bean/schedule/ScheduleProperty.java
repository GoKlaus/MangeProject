package org.industry.common.sdk.bean.schedule;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScheduleProperty {
    private ScheduleConfig read;
    private ScheduleConfig custom;
    private ScheduleConfig status;
}

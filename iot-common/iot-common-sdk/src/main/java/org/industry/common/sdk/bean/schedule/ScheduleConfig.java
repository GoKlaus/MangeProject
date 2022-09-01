package org.industry.common.sdk.bean.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleConfig {
    private Boolean enable = false;
    private String corn = "* */1 * * * ?";
}

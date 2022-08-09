package org.industry.common.property;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class ThreadProperty {
    private String prefix;
    private int corePoolSize;
    private int maximumPoolSize;
    private int keepAliveTime;
}

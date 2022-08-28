package org.industry.common.bean.batch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BatchDevice implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Boolean multi;

    /**
     * 仅当share为false的时候生效
     */
    private Map<String, Map<String, String>> pointConfig;
}

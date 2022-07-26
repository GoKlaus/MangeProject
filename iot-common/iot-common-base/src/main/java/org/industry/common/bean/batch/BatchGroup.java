package org.industry.common.bean.batch;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BatchGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private List<BatchDevice> devices;
}

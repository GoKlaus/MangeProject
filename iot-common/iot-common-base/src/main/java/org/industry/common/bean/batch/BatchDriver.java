package org.industry.common.bean.batch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BatchDriver implements Serializable {
    private static final long serialVersionUID = 1L;

    private String serviceName;

    private List<BatchProfile> profiles;
}

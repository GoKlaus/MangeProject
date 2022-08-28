package org.industry.common.bean.driver;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.industry.common.model.Driver;
import org.industry.common.model.DriverAttribute;
import org.industry.common.model.PointAttribute;

import java.io.Serializable;
import java.util.List;

/**
 * @author pnoker
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverRegister implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tenant;
    private Driver driver;
    private List<DriverAttribute> driverAttributes;
    private List<PointAttribute> pointAttributes;

}

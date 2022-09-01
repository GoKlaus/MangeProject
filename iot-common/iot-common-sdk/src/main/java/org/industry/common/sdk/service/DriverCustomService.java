package org.industry.common.sdk.service;

import org.industry.common.bean.driver.AttributeInfo;
import org.industry.common.model.Device;
import org.industry.common.model.Point;

import java.util.Map;

public interface DriverCustomService {


    /**
     * Initial Driver
     */
    void initial();

    String read(Map<String, AttributeInfo> driverInfo,
                Map<String, AttributeInfo> pointInfo,
                Device device,
                Point point);

    Boolean write(Map<String, AttributeInfo> driverInfo, Map<String, AttributeInfo> pointInfo, Device device, AttributeInfo attributeInfo);

    /**
     * Schedule Operation
     */
    void schedule();

}

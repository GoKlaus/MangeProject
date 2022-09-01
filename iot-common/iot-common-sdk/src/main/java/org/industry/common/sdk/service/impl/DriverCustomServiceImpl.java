package org.industry.common.sdk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.driver.AttributeInfo;
import org.industry.common.model.Device;
import org.industry.common.model.Point;
import org.industry.common.sdk.service.DriverCustomService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class DriverCustomServiceImpl implements DriverCustomService {


    /**
     * Initial Driver
     */
    @Override
    public void initial() {

    }

    /**
     * Schedule Operation
     */
    @Override
    public void schedule() {

    }

    /**
     * @param driverInfo
     * @param pointInfo
     * @param device
     * @param point
     * @return
     */
    @Override
    public String read(Map<String, AttributeInfo> driverInfo, Map<String, AttributeInfo> pointInfo, Device device, Point point) {
        return null;
    }

    /**
     * @param driverInfo
     * @param pointInfo
     * @param device
     * @param attributeInfo
     * @return
     */
    @Override
    public Boolean write(Map<String, AttributeInfo> driverInfo, Map<String, AttributeInfo> pointInfo, Device device, AttributeInfo attributeInfo) {
        return null;
    }


}

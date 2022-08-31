package org.industry.common.sdk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.point.PointValue;
import org.industry.common.exception.ServiceException;
import org.industry.common.model.Device;
import org.industry.common.sdk.service.DriverCommandService;
import org.industry.common.sdk.bean.driver.DriverContext;
import org.industry.common.sdk.service.DriverCustomService;
import org.industry.common.sdk.service.DriverService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class DriverCommandServiceImpl implements DriverCommandService {

    @Resource
    private DriverService driverService;
    @Resource
    private DriverContext driverContext;
    @Resource
    private DriverCustomService driverCustomService;

    /**
     * @param deviceId
     * @param pointId
     * @return
     */
    @Override
    public PointValue read(String deviceId, String pointId) {
        Device device =driverContext.getDeviceByDeviceId(deviceId);
        try {
       String rawValue=  driverCustomService.read(driverContext.getDriverInfoByDeviceId(deviceId),
                 driverContext.getPointInfoByDeviceIdAndPointId(deviceId, pointId),
                 device,
                 driverContext.getPointByDeviceIdAndPointId(deviceId,pointId));
            PointValue pointValue = new PointValue(deviceId, pointId, rawValue, driverService.convertValue(deviceId, pointId, rawValue));
            driverService.pointValueSender(pointValue);
            return pointValue;
        } catch(Exception e) {
        throw new ServiceException(e.getMessage());
        }
    }
}

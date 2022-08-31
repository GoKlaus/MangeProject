package org.industry.common.sdk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.point.PointValue;
import org.industry.common.sdk.service.DriverService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DriverServiceImpl implements DriverService {
    /**
     * @param deviceId
     * @param pointId
     * @param rawValue
     * @return
     */
    @Override
    public String convertValue(String deviceId, String pointId, String rawValue) {
        return null;
    }

    /**
     * @param pointValue
     */
    @Override
    public void pointValueSender(PointValue pointValue) {

    }
}

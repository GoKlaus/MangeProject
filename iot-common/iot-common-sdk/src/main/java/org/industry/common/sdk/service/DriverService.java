package org.industry.common.sdk.service;

import org.industry.common.bean.point.PointValue;

public interface DriverService {
    String convertValue(String deviceId, String pointId, String rawValue);


    void pointValueSender(PointValue pointValue);
}

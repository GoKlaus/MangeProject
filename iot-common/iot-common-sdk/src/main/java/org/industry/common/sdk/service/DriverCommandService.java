package org.industry.common.sdk.service;

import org.industry.common.bean.point.PointValue;

public interface DriverCommandService {
    /**
     *
     * @param deviceId
     * @param pointId
     * @return
     */
    PointValue read(String deviceId, String pointId);

    /**
     * 写操作
     * @param deviceId
     * @param pointId
     * @param value
     * @return
     */
    Boolean write(String deviceId, String pointId, String value);
}

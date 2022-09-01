package org.industry.common.sdk.service;

import org.industry.common.bean.point.PointValue;
import org.industry.common.model.DeviceEvent;
import org.industry.common.model.DriverEvent;

import java.util.List;

public interface DriverService {

    /**
     * 将位号原始值进行处理和转换
     *
     * @param deviceId
     * @param pointId
     * @param rawValue
     * @return
     */
    String convertValue(String deviceId, String pointId, String rawValue);

    /**
     * 发送驱动事件
     * @param driverEvent
     */
    void driverEventSender(DriverEvent driverEvent);

    /**
     * 发送设备事件
     * @param deviceEvent
     */
    void deviceEventSender(DeviceEvent deviceEvent);

    /**
     * 发送设备事件
     * @param deviceId
     * @param type
     * @param content
     */
    void deviceEventSender(String deviceId, String type, String content);

    /**
     * 发送设备事件
     * @param deviceId
     * @param pointId
     * @param type
     * @param content
     */
    void deviceEventSender(String deviceId, String pointId, String type, String content);


    /**
     * 发送位号值到消息组件
     * @param pointValue
     */
    void pointValueSender(PointValue pointValue);

    /**
     * 批量发送位号值到消息组件
     * @param pointValues
     */
    void pointValueSender(List<PointValue> pointValues);

    /**
     * Close ApplicationContext
     * @param template
     * @param params
     */
    void close(CharSequence template, Object... params);
}

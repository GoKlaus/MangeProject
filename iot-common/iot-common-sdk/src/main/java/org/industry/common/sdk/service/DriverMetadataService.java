package org.industry.common.sdk.service;

import org.industry.common.model.*;

public interface DriverMetadataService {
    /**
     * 初始化 SDK
     */
    void initial();

    /**
     * 向 DeviceDriver 中添加模板
     *
     * @param profile
     */
    void upsertProfile(Profile profile);

    /**
     * 删除 DeviceDriver 中模板
     *
     * @param id
     */
    void deleteProfile(String id);

    /**
     * 向 DeviceDriver 中添加设备
     *
     * @param device
     */
    void upsertDevice(Device device);

    /**
     * 删除 DeviceDriver 中设备
     *
     * @param id
     */
    void deleteDevice(String id);

    /**
     * 向 DeviceDriver 中添加位号
     *
     * @param point
     */
    void upsertPoint(Point point);

    /**
     * 删除 DeviceDriver 中位号
     *
     * @param profileId
     * @param id
     */
    void deletePoint(String profileId, String id);

    /**
     * 向 DeviceDriver 中添加驱动配置信息
     *
     * @param driverInfo
     */
    void upsertDriverInfo(DriverInfo driverInfo);

    /**
     * 删除 DeviceDriver 中添加驱动配置信息
     *
     * @param deviceId
     * @param attributeId
     */
    void deleteDriverInfo(String deviceId, String attributeId);

    /**
     * 向 DeviceDriver 中添加位号配置信息
     *
     * @param pointInfo
     */
    void upsertPointInfo(PointInfo pointInfo);

    /**
     * 删除 DeviceDriver 中添加位号配置信息
     *
     * @param deviceId
     * @param pointId
     * @param attributeId
     */
    void deletePointInfo(String deviceId, String pointId, String attributeId);
}

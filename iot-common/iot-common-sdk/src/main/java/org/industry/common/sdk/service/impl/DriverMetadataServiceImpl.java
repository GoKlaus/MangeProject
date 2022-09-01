package org.industry.common.sdk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.industry.common.model.*;
import org.industry.common.sdk.service.DriverMetadataService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DriverMetadataServiceImpl implements DriverMetadataService {
    /**
     * 初始化 SDK
     */
    @Override
    public void initial() {

    }

    /**
     * 向 DeviceDriver 中添加模板
     *
     * @param profile
     */
    @Override
    public void upsertProfile(Profile profile) {

    }

    /**
     * 删除 DeviceDriver 中模板
     *
     * @param id
     */
    @Override
    public void deleteProfile(String id) {

    }

    /**
     * 向 DeviceDriver 中添加设备
     *
     * @param device
     */
    @Override
    public void upsertDevice(Device device) {

    }

    /**
     * 删除 DeviceDriver 中设备
     *
     * @param id
     */
    @Override
    public void deleteDevice(String id) {

    }

    /**
     * 向 DeviceDriver 中添加位号
     *
     * @param point
     */
    @Override
    public void upsertPoint(Point point) {

    }

    /**
     * 删除 DeviceDriver 中位号
     *
     * @param profileId
     * @param id
     */
    @Override
    public void deletePoint(String profileId, String id) {

    }

    /**
     * 向 DeviceDriver 中添加驱动配置信息
     *
     * @param driverInfo
     */
    @Override
    public void upsertDriverInfo(DriverInfo driverInfo) {

    }

    /**
     * 删除 DeviceDriver 中添加驱动配置信息
     *
     * @param deviceId
     * @param attributeId
     */
    @Override
    public void deleteDriverInfo(String deviceId, String attributeId) {

    }

    /**
     * 向 DeviceDriver 中添加位号配置信息
     *
     * @param pointInfo
     */
    @Override
    public void upsertPointInfo(PointInfo pointInfo) {

    }

    /**
     * 删除 DeviceDriver 中添加位号配置信息
     *
     * @param deviceId
     * @param pointId
     * @param attributeId
     */
    @Override
    public void deletePointInfo(String deviceId, String pointId, String attributeId) {

    }
}

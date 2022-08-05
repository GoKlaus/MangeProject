package org.industry.center.manager.service;

import org.industry.common.base.Service;
import org.industry.common.dto.DeviceDto;
import org.industry.common.model.Device;

import java.util.List;
import java.util.Set;

public interface DeviceService extends Service<Device, DeviceDto> {

    /**
     * 根据 设备Name 和 租户Id 查询设备
     *
     * @param name     Device Name
     * @param tenantId Tenant Id
     * @return Device
     */
    Device selectByName(String name, String tenantId);

    /**
     * 根据 驱动Id 查询该驱动下的全部设备
     *
     * @param driverId Driver Id
     * @return Device Array
     */
    List<Device> selectByDriverId(String driverId);

    /**
     * 根据 模板Id 查询该驱动下的全部设备
     *
     * @param profileId Profile Id
     * @return Device Array
     */
    List<Device> selectByProfileId(String profileId);

    /**
     * 根据 设备Id集 查询设备
     *
     * @param ids Device Id Set
     * @return Device Array
     */
    List<Device> selectByIds(Set<String> ids);

}

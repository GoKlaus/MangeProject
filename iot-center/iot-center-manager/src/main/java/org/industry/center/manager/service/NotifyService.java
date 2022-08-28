package org.industry.center.manager.service;

import org.industry.common.model.*;

/**
 * Notify Interface
 *
 * @author pnoker
 */
public interface NotifyService {

    /**
     * 通知驱动 新增模板(ADD) / 删除模板(DELETE) / 修改模板(UPDATE)
     *
     * @param command Operation Type
     * @param profile Profile
     */
    void notifyDriverProfile(String command, Profile profile);

    /**
     * 通知驱动 新增位号(ADD) / 删除位号(DELETE) / 修改位号(UPDATE)
     *
     * @param command Operation Type
     * @param point   Point
     */
    void notifyDriverPoint(String command, Point point);

    /**
     * 通知驱动 新增设备(ADD) / 删除设备(DELETE) / 修改设备(UPDATE)
     *
     * @param command Operation Type
     * @param device  Device
     */
    void notifyDriverDevice(String command, Device device);

    /**
     * 通知驱动 新增驱动配置(ADD) / 删除驱动配置(DELETE) / 更新驱动配置(UPDATE)
     *
     * @param command    Operation Type
     * @param driverInfo Driver Info
     */
    void notifyDriverDriverInfo(String command, DriverInfo driverInfo);

    /**
     * 通知驱动 新增位号配置(ADD) / 删除位号配置(DELETE) / 更新位号配置(UPDATE)
     *
     * @param command   Operation Type
     * @param pointInfo PointInfo
     */
    void notifyDriverPointInfo(String command, PointInfo pointInfo);

}

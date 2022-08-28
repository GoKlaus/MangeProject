/*
 * Copyright (c) 2022. Pnoker. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.industry.center.manager.service.impl;

import org.industry.center.manager.service.DriverService;
import org.industry.center.manager.service.NotifyService;
import org.industry.common.bean.driver.DriverConfiguration;
import org.industry.common.constant.CommonConstant;
import org.industry.common.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * NotifyService Impl
 *
 * @author pnoker
 */
@Slf4j
@Service
public class NotifyServiceImpl implements NotifyService {

    @Resource
    private DriverService driverService;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void notifyDriverProfile(String command, Profile profile) {
        try {
            List<Driver> drivers = driverService.selectByProfileId(profile.getId());
            drivers.forEach(driver -> {
                DriverConfiguration operation = new DriverConfiguration().setType(CommonConstant.Driver.Type.PROFILE).setCommand(command).setContent(profile);
                notifyDriver(driver, operation);
            });
        } catch (Exception e) {
            log.warn("Notify Driver Profile: {}", e.getMessage());
        }
    }

    @Override
    public void notifyDriverPoint(String command, Point point) {
        try {
            List<Driver> drivers = driverService.selectByProfileId(point.getProfileId());
            drivers.forEach(driver -> {
                DriverConfiguration operation = new DriverConfiguration().setType(CommonConstant.Driver.Type.POINT).setCommand(command).setContent(point);
                notifyDriver(driver, operation);
            });
        } catch (Exception e) {
            log.warn("Notify Driver Point: {}", e.getMessage());
        }
    }

    @Override
    public void notifyDriverDevice(String command, Device device) {
        try {
            Driver driver = driverService.selectById(device.getDriverId());
            DriverConfiguration operation = new DriverConfiguration().setType(CommonConstant.Driver.Type.DEVICE).setCommand(command).setContent(device);
            notifyDriver(driver, operation);
        } catch (Exception e) {
            log.warn("Notify Driver Device: {}", e.getMessage());
        }
    }

    @Override
    public void notifyDriverDriverInfo(String command, DriverInfo driverInfo) {
        try {
            Driver driver = driverService.selectByDeviceId(driverInfo.getDeviceId());
            DriverConfiguration operation = new DriverConfiguration().setType(CommonConstant.Driver.Type.DRIVER_INFO).setCommand(command).setContent(driverInfo);
            notifyDriver(driver, operation);
        } catch (Exception e) {
            log.warn("Notify Driver DriverInfo: {}", e.getMessage());
        }
    }

    @Override
    public void notifyDriverPointInfo(String command, PointInfo pointInfo) {
        try {
            Driver driver = driverService.selectByDeviceId(pointInfo.getDeviceId());
            DriverConfiguration operation = new DriverConfiguration().setType(CommonConstant.Driver.Type.POINT_INFO).setCommand(command).setContent(pointInfo);
            notifyDriver(driver, operation);
        } catch (Exception e) {
            log.warn("Notify Driver PointInfo: {}", e.getMessage());
        }
    }

    /**
     * notify driver
     *
     * @param driver              Driver
     * @param driverConfiguration DriverConfiguration
     */
    private void notifyDriver(Driver driver, DriverConfiguration driverConfiguration) {
        log.debug("Notify Driver {} : {}", driver.getServiceName(), driverConfiguration);
        rabbitTemplate.convertAndSend(CommonConstant.Rabbit.TOPIC_EXCHANGE_METADATA, CommonConstant.Rabbit.ROUTING_DRIVER_METADATA_PREFIX + driver.getServiceName(), driverConfiguration);
    }

}

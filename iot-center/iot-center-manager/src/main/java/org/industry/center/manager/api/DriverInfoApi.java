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

package org.industry.center.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.api.manager.feign.DriverInfoClient;
import org.industry.center.manager.service.DriverInfoService;
import org.industry.center.manager.service.NotifyService;
import org.industry.common.bean.R;
import org.industry.common.constant.CommonConstant;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.DriverInfoDto;
import org.industry.common.model.DriverInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 位号配置信息 Client 接口实现
 *
 * @author pnoker
 */
@Slf4j
@RestController
@RequestMapping(ServiceConstant.Manager.DRIVER_INFO_URL_PREFIX)
public class DriverInfoApi implements DriverInfoClient {

    @Resource
    private DriverInfoService driverInfoService;
    @Resource
    private NotifyService notifyService;

    @Override
    public R<DriverInfo> add(DriverInfo driverInfo) {
        try {
            DriverInfo add = driverInfoService.add(driverInfo);
            if (null != add) {
                notifyService.notifyDriverDriverInfo(CommonConstant.Driver.DriverInfo.ADD, add);
                return R.ok(add);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Boolean> delete(String id) {
        try {
            DriverInfo driverInfo = driverInfoService.selectById(id);
            if (null != driverInfo && driverInfoService.delete(id)) {
                notifyService.notifyDriverDriverInfo(CommonConstant.Driver.DriverInfo.DELETE, driverInfo);
                return R.ok();
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<DriverInfo> update(DriverInfo driverInfo) {
        try {
            DriverInfo update = driverInfoService.update(driverInfo);
            if (null != update) {
                notifyService.notifyDriverDriverInfo(CommonConstant.Driver.DriverInfo.UPDATE, update);
                return R.ok(update);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<DriverInfo> selectById(String id) {
        try {
            DriverInfo select = driverInfoService.selectById(id);
            if (null != select) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<DriverInfo> selectByAttributeIdAndDeviceId(String attributeId, String deviceId) {
        try {
            DriverInfo select = driverInfoService.selectByAttributeIdAndDeviceId(attributeId, deviceId);
            if (null != select) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<List<DriverInfo>> selectByDeviceId(String deviceId) {
        try {
            List<DriverInfo> select = driverInfoService.selectByDeviceId(deviceId);
            if (null != select) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Page<DriverInfo>> list(DriverInfoDto driverInfoDto) {
        try {
            Page<DriverInfo> page = driverInfoService.list(driverInfoDto);
            if (null != page) {
                return R.ok(page);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

}

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
import org.industry.api.manager.feign.DriverClient;
import org.industry.center.manager.service.DriverService;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.DriverDto;
import org.industry.common.model.Driver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 驱动 Client 接口实现
 *
 * @author pnoker
 */
@Slf4j
@RestController
@RequestMapping(ServiceConstant.Manager.DRIVER_URL_PREFIX)
public class DriverApi implements DriverClient {
    @Resource
    private DriverService driverService;

    @Override
    public R<Driver> add(Driver driver, String tenantId) {
        try {
            Driver add = driverService.add(driver.setTenantId(tenantId));
            if (null != add) {
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
            return driverService.delete(id) ? R.ok() : R.fail();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<Driver> update(Driver driver, String tenantId) {
        try {
            Driver update = driverService.update(driver.setTenantId(tenantId));
            if (null != update) {
                return R.ok(update);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Driver> selectById(String id) {
        try {
            Driver select = driverService.selectById(id);
            return R.ok(select);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<Driver> selectByServiceName(String serviceName) {
        try {
            Driver select = driverService.selectByServiceName(serviceName);
            return R.ok(select);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<Driver> selectByHostPort(String type, String host, Integer port, String tenantId) {
        try {
            Driver select = driverService.selectByHostPort(type, host, port, tenantId);
            return R.ok(select);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<Page<Driver>> list(DriverDto driverDto, String tenantId) {
        try {
            driverDto.setTenantId(tenantId);
            Page<Driver> page = driverService.list(driverDto);
            if (null != page) {
                return R.ok(page);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

}

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
import org.industry.api.manager.feign.DriverAttributeClient;
import org.industry.center.manager.service.DriverAttributeService;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.DriverAttributeDto;
import org.industry.common.exception.NotFoundException;
import org.industry.common.model.DriverAttribute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 驱动连接配置信息 Client 接口实现
 *
 * @author pnoker
 */
@Slf4j
@RestController
@RequestMapping(ServiceConstant.Manager.DRIVER_ATTRIBUTE_URL_PREFIX)
public class DriverAttributeApi implements DriverAttributeClient {
    @Resource
    private DriverAttributeService driverAttributeService;

    @Override
    public R<DriverAttribute> add(DriverAttribute driverAttribute) {
        try {
            DriverAttribute add = driverAttributeService.add(driverAttribute);
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
            return driverAttributeService.delete(id) ? R.ok() : R.fail();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<DriverAttribute> update(DriverAttribute driverAttribute) {
        try {
            DriverAttribute update = driverAttributeService.update(driverAttribute);
            if (null != update) {
                return R.ok(update);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<DriverAttribute> selectById(String id) {
        try {
            DriverAttribute select = driverAttributeService.selectById(id);
            if (null != select) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<List<DriverAttribute>> selectByDriverId(String id) {
        try {
            List<DriverAttribute> select = driverAttributeService.selectByDriverId(id);
            if (null != select) {
                return R.ok(select);
            }
        } catch (NotFoundException ne) {
            return R.ok(new ArrayList<>());
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Page<DriverAttribute>> list(DriverAttributeDto driverAttributeDto) {
        try {
            Page<DriverAttribute> page = driverAttributeService.list(driverAttributeDto);
            if (null != page) {
                return R.ok(page);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

}

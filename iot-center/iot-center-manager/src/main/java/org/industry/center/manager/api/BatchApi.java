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

import cn.hutool.core.util.StrUtil;
import org.industry.api.manager.feign.BatchClient;
import org.industry.center.manager.service.BatchService;
import org.industry.common.bean.R;
import org.industry.common.bean.batch.BatchDriver;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.exception.ServiceException;
import org.industry.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 批量导入 Client 接口实现
 *
 * @author pnoker
 */
@Slf4j
@RestController
@RequestMapping(ServiceConstant.Manager.BATCH_URL_PREFIX)
public class BatchApi implements BatchClient {

    @Resource
    private BatchService batchService;


    @Override
    public R<Boolean> batchImport(MultipartFile multipartFile) {
        try {
            if (multipartFile.isEmpty()) {
                throw new ServiceException("Import file is empty");
            }
            // Convert json file to BatchDriver Array
            List<BatchDriver> batchDrivers = JsonUtil.parseArray(multipartFile.getInputStream(), BatchDriver.class);
            if (null == batchDrivers) {
                throw new ServiceException("Import file is blank");
            }
            batchService.batchImport(batchDrivers);
            return R.ok();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<Boolean> batchImport(List<BatchDriver> batchDrivers) {
        try {
            if (null == batchDrivers) {
                throw new ServiceException("Import file is blank");
            }
            batchService.batchImport(batchDrivers);
            return R.ok();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<BatchDriver> batchExport(String serviceName) {
        try {
            if (StrUtil.hasBlank(serviceName)) {
                throw new ServiceException("Export driver service name is blank");
            }
            BatchDriver batchDriver = batchService.batchExport(serviceName);
            if (null != batchDriver) {
                return R.ok(batchDriver);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

}

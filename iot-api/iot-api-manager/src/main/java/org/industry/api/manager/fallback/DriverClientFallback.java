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

package org.industry.api.manager.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.api.manager.feign.DriverClient;
import org.industry.common.bean.R;
import org.industry.common.dto.DriverDto;
import org.industry.common.model.Driver;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * DriverClientFallback
 *
 * @author pnoker
 */
@Slf4j
@Component
public class DriverClientFallback implements FallbackFactory<DriverClient> {

    @Override
    public DriverClient create(Throwable throwable) {
        String message = throwable.getMessage() == null ? "No available server for client: DC3-CENTER-MANAGER" : throwable.getMessage();
        log.error("Fallback:{}", message);

        return new DriverClient() {

            @Override
            public R<Driver> add(Driver driver, String tenantId) {
                return R.fail(message);
            }

            @Override
            public R<Boolean> delete(String id) {
                return R.fail(message);
            }

            @Override
            public R<Driver> update(Driver driver, String tenantId) {
                return R.fail(message);
            }

            @Override
            public R<Driver> selectById(String id) {
                return R.fail(message);
            }

            @Override
            public R<Driver> selectByServiceName(String serviceName) {
                return R.fail(message);
            }

            @Override
            public R<Driver> selectByHostPort(String type, String host, Integer port, String tenantId) {
                return R.fail(message);
            }

            @Override
            public R<Page<Driver>> list(DriverDto driverDto, String tenantId) {
                return R.fail(message);
            }

        };
    }
}
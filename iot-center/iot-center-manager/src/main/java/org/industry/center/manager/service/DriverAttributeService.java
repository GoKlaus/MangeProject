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

package org.industry.center.manager.service;

import org.industry.common.base.Service;
import org.industry.common.dto.DriverAttributeDto;
import org.industry.common.model.DriverAttribute;

import java.util.List;

/**
 * DriverAttribute Interface
 *
 * @author pnoker
 */
public interface DriverAttributeService extends Service<DriverAttribute, DriverAttributeDto> {

    /**
     * 根据驱动配置属性 NAME 和 驱动 ID 查询
     *
     * @param name     属性名称
     * @param driverId 驱动ID
     * @return DriverAttribute
     */
    DriverAttribute selectByNameAndDriverId(String name, String driverId);

    /**
     * 根据驱动 ID 查询
     *
     * @param driverId 驱动ID
     * @return DriverAttribute Array
     */
    List<DriverAttribute> selectByDriverId(String driverId);
}

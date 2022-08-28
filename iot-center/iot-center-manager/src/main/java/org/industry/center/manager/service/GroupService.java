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
import org.industry.common.dto.GroupDto;
import org.industry.common.model.Group;

/**
 * Group Interface
 *
 * @author pnoker
 */
public interface GroupService extends Service<Group, GroupDto> {
    /**
     * 根据分组 NAME 查询
     *
     * @param name
     * @return
     */
    Group selectByName(String name, String tenantId);

}

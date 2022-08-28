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

package org.industry.api.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.api.manager.fallback.LabelClientFallback;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.LabelDto;
import org.industry.common.model.Label;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 标签 FeignClient
 *
 * @author pnoker
 */
@FeignClient(path = ServiceConstant.Manager.LABEL_URL_PREFIX, name = ServiceConstant.Manager.SERVICE_NAME, fallbackFactory = LabelClientFallback.class)
public interface LabelClient {

    /**
     * 新增 Label
     *
     * @param label Label
     * @return Label
     */
    @PostMapping("/add")
    R<Label> add(@Validated(Insert.class) @RequestBody Label label, @RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 根据 ID 删除 Label
     *
     * @param id Label Id
     * @return Boolean
     */
    @PostMapping("/delete/{id}")
    R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改 Label
     *
     * @param label Label
     * @return Label
     */
    @PostMapping("/update")
    R<Label> update(@Validated(Update.class) @RequestBody Label label, @RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 根据 ID 查询 Label
     *
     * @param id Label Id
     * @return Label
     */
    @GetMapping("/id/{id}")
    R<Label> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询 Label
     *
     * @param labelDto Label Dto
     * @return Page<Label>
     */
    @PostMapping("/list")
    R<Page<Label>> list(@RequestBody(required = false) LabelDto labelDto, @RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

}

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
import org.industry.api.manager.fallback.PointAttributeClientFallback;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.PointAttributeDto;
import org.industry.common.model.PointAttribute;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 位号配置属性 FeignClient
 *
 * @author pnoker
 */
@FeignClient(path = ServiceConstant.Manager.POINT_ATTRIBUTE_URL_PREFIX, name = ServiceConstant.Manager.SERVICE_NAME, fallbackFactory = PointAttributeClientFallback.class)
public interface PointAttributeClient {

    /**
     * 新增 PointAttribute
     *
     * @param pointAttribute PointAttribute
     * @return PointAttribute
     */
    @PostMapping("/add")
    R<PointAttribute> add(@Validated(Insert.class) @RequestBody PointAttribute pointAttribute);

    /**
     * 根据 ID 删除 PointAttribute
     *
     * @param id PointAttribute Id
     * @return Boolean
     */
    @PostMapping("/delete/{id}")
    R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改 PointAttribute
     *
     * @param pointAttribute PointAttribute
     * @return PointAttribute
     */
    @PostMapping("/update")
    R<PointAttribute> update(@Validated(Update.class) @RequestBody PointAttribute pointAttribute);

    /**
     * 根据 ID 查询 PointAttribute
     *
     * @param id PointAttribute Id
     * @return PointAttribute
     */
    @GetMapping("/id/{id}")
    R<PointAttribute> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 根据 驱动ID 查询 PointAttribute
     *
     * @param id PointAttribute Id
     * @return PointAttribute Array
     */
    @GetMapping("/driver_id/{id}")
    R<List<PointAttribute>> selectByDriverId(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询 PointAttribute
     *
     * @param pointAttributeDto PointAttribute Dto
     * @return Page<PointAttribute>
     */
    @PostMapping("/list")
    R<Page<PointAttribute>> list(@RequestBody(required = false) PointAttributeDto pointAttributeDto);

}

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
import org.industry.api.manager.feign.LabelClient;
import org.industry.center.manager.service.LabelService;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.LabelDto;
import org.industry.common.model.Label;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 标签 Client 接口实现
 *
 * @author pnoker
 */
@Slf4j
@RestController
@RequestMapping(ServiceConstant.Manager.LABEL_URL_PREFIX)
public class LabelApi implements LabelClient {

    @Resource
    private LabelService labelService;

    @Override
    public R<Label> add(Label label, String tenantId) {
        try {
            Label add = labelService.add(label.setTenantId(tenantId));
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
            return labelService.delete(id) ? R.ok() : R.fail();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<Label> update(Label label, String tenantId) {
        try {
            Label update = labelService.update(label.setTenantId(tenantId));
            if (null != update) {
                return R.ok(update);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Label> selectById(String id) {
        try {
            Label select = labelService.selectById(id);
            if (null != select) {
                return R.ok(select);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<Page<Label>> list(LabelDto labelDto, String tenantId) {
        try {
            labelDto.setTenantId(tenantId);
            Page<Label> page = labelService.list(labelDto);
            if (null != page) {
                return R.ok(page);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }
}

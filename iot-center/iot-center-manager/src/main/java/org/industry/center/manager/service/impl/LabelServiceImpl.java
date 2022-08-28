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

package org.industry.center.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.center.manager.mapper.LabelMapper;
import org.industry.center.manager.service.LabelBindService;
import org.industry.center.manager.service.LabelService;
import org.industry.common.bean.Pages;
import org.industry.common.constant.CacheConstant;
import org.industry.common.dto.LabelBindDto;
import org.industry.common.dto.LabelDto;
import org.industry.common.exception.NotFoundException;
import org.industry.common.exception.ServiceException;
import org.industry.common.model.Label;
import org.industry.common.model.LabelBind;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * LabelService Impl
 *
 * @author pnoker
 */
@Slf4j
@Service
public class LabelServiceImpl implements LabelService {

    @Resource
    private LabelBindService labelBindService;
    @Resource
    private LabelMapper labelMapper;

    @Override
    @Caching(
            put = {
                    @CachePut(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.ID, key = "#label.id", condition = "#result!=null"),
                    @CachePut(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.NAME, key = "#label.name+'.'+#label.tenantId", condition = "#result!=null")
            },
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null")
            }
    )
    public Label add(Label label) {
        selectByName(label.getName(), label.getTenantId());
        if (labelMapper.insert(label) > 0) {
            return labelMapper.selectById(label.getId());
        }
        throw new ServiceException("The label add failed");
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.ID, key = "#id", condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.NAME, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result==true")
            }
    )
    public boolean delete(String id) {
        LabelBindDto labelBindDto = new LabelBindDto();
        labelBindDto.setLabelId(id);
        Page<LabelBind> labelBindPage = labelBindService.list(labelBindDto);
        if (labelBindPage.getTotal() > 0) {
            throw new ServiceException("The label already bound by the entity");
        }
        Label label = selectById(id);
        if (null == label) {
            throw new NotFoundException("The label does not exist");
        }
        return labelMapper.deleteById(id) > 0;
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.ID, key = "#label.id", condition = "#result!=null"),
                    @CachePut(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.NAME, key = "#label.name+'.'+#label.tenantId", condition = "#result!=null")
            },
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null")
            }
    )
    public Label update(Label label) {
        selectById(label.getId());
        label.setUpdateTime(null);
        if (labelMapper.updateById(label) > 0) {
            Label select = labelMapper.selectById(label.getId());
            label.setName(select.getName());
            return select;
        }
        throw new ServiceException("The label update failed");
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.ID, key = "#id", unless = "#result==null")
    public Label selectById(String id) {
        Label label = labelMapper.selectById(id);
        if (null == label) {
            throw new NotFoundException("The label does not exist");
        }
        return label;
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.NAME, key = "#name+'.'+#tenantId", unless = "#result==null")
    public Label selectByName(String name, String tenantId) {
        LambdaQueryWrapper<Label> queryWrapper = Wrappers.<Label>query().lambda();
        queryWrapper.eq(Label::getName, name);
        queryWrapper.eq(Label::getTenantId, tenantId);
        Label label = labelMapper.selectOne(queryWrapper);
        if (null == label) {
            throw new NotFoundException("The label does not exist");
        }
        return label;
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.LABEL + CacheConstant.Suffix.LIST, keyGenerator = "commonKeyGenerator", unless = "#result==null")
    public Page<Label> list(LabelDto labelDto) {
        if (!Optional.ofNullable(labelDto.getPage()).isPresent()) {
            labelDto.setPage(new Pages());
        }
        return labelMapper.selectPage(labelDto.getPage().convert(), fuzzyQuery(labelDto));
    }

    @Override
    public LambdaQueryWrapper<Label> fuzzyQuery(LabelDto labelDto) {
        LambdaQueryWrapper<Label> queryWrapper = Wrappers.<Label>query().lambda();
        if (null != labelDto) {
            if (StrUtil.isNotBlank(labelDto.getName())) {
                queryWrapper.like(Label::getName, labelDto.getName());
            }
            if (StrUtil.isNotBlank(labelDto.getColor())) {
                queryWrapper.eq(Label::getColor, labelDto.getColor());
            }
            if (null != labelDto.getTenantId()) {
                queryWrapper.eq(Label::getTenantId, labelDto.getTenantId());
            }
        }
        return queryWrapper;
    }

}

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
import org.industry.center.manager.mapper.DriverAttributeMapper;
import org.industry.center.manager.service.DriverAttributeService;
import org.industry.common.bean.Pages;
import org.industry.common.constant.CacheConstant;
import org.industry.common.dto.DriverAttributeDto;
import org.industry.common.exception.DuplicateException;
import org.industry.common.exception.NotFoundException;
import org.industry.common.exception.ServiceException;
import org.industry.common.model.DriverAttribute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * DriverAttributeService Impl
 *
 * @author pnoker
 */
@Slf4j
@Service
public class DriverAttributeServiceImpl implements DriverAttributeService {
    @Resource
    private DriverAttributeMapper driverAttributeMapper;

    @Override
    @Caching(
            put = {
                    @CachePut(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.ID, key = "#driverAttribute.id", condition = "#result!=null"),
                    @CachePut(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.NAME + CacheConstant.Suffix.DRIVER_ID, key = "#driverAttribute.name+'.'+#driverAttribute.driverId", condition = "#result!=null")
            },
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.DRIVER_ID + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null")
            }
    )
    public DriverAttribute add(DriverAttribute driverAttribute) {
        try {
            selectByNameAndDriverId(driverAttribute.getName(), driverAttribute.getDriverId());
            throw new DuplicateException("The driver attribute already exists");
        } catch (NotFoundException notFoundException) {
            if (driverAttributeMapper.insert(driverAttribute) > 0) {
                return driverAttributeMapper.selectById(driverAttribute.getId());
            }
            throw new ServiceException("The driver attribute add failed");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.ID, key = "#id", condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.NAME + CacheConstant.Suffix.DRIVER_ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.DRIVER_ID + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result==true")
            }
    )
    public boolean delete(String id) {
        selectById(id);
        return driverAttributeMapper.deleteById(id) > 0;
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.ID, key = "#driverAttribute.id", condition = "#result!=null"),
                    @CachePut(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.NAME + CacheConstant.Suffix.DRIVER_ID, key = "#driverAttribute.name+'.'+#driverAttribute.driverId", condition = "#result!=null")
            },
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.DRIVER_ID + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null")
            }
    )
    public DriverAttribute update(DriverAttribute driverAttribute) {
        selectById(driverAttribute.getId());
        driverAttribute.setUpdateTime(null);
        if (driverAttributeMapper.updateById(driverAttribute) > 0) {
            DriverAttribute select = driverAttributeMapper.selectById(driverAttribute.getId());
            driverAttribute.setName(select.getName()).setDriverId(select.getDriverId());
            return select;
        }
        throw new ServiceException("The driver attribute update failed");
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.ID, key = "#id", unless = "#result==null")
    public DriverAttribute selectById(String id) {
        DriverAttribute driverAttribute = driverAttributeMapper.selectById(id);
        if (null == driverAttribute) {
            throw new NotFoundException("The driver attribute does not exist");
        }
        return driverAttribute;
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.NAME + CacheConstant.Suffix.DRIVER_ID, key = "#name+'.'+#driverId", unless = "#result==null")
    public DriverAttribute selectByNameAndDriverId(String name, String driverId) {
        LambdaQueryWrapper<DriverAttribute> queryWrapper = Wrappers.<DriverAttribute>query().lambda();
        queryWrapper.eq(DriverAttribute::getName, name);
        queryWrapper.eq(DriverAttribute::getDriverId, driverId);
        DriverAttribute driverAttribute = driverAttributeMapper.selectOne(queryWrapper);
        if (null == driverAttribute) {
            throw new NotFoundException("The driver attribute does not exist");
        }
        return driverAttribute;
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.DRIVER_ID + CacheConstant.Suffix.LIST, key = "#driverId", unless = "#result==null")
    public List<DriverAttribute> selectByDriverId(String driverId) {
        DriverAttributeDto driverAttributeDto = new DriverAttributeDto();
        driverAttributeDto.setDriverId(driverId);
        List<DriverAttribute> driverAttributes = driverAttributeMapper.selectList(fuzzyQuery(driverAttributeDto));
        if (null == driverAttributes || driverAttributes.size() < 1) {
            throw new NotFoundException("The driver attributes does not exist");
        }
        return driverAttributes;
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.DRIVER_ATTRIBUTE + CacheConstant.Suffix.LIST, keyGenerator = "commonKeyGenerator", unless = "#result==null")
    public Page<DriverAttribute> list(DriverAttributeDto driverAttributeDto) {
        if (null == driverAttributeDto.getPage()) {
            driverAttributeDto.setPage(new Pages());
        }
        return driverAttributeMapper.selectPage(driverAttributeDto.getPage().convert(), fuzzyQuery(driverAttributeDto));
    }

    @Override
    public LambdaQueryWrapper<DriverAttribute> fuzzyQuery(DriverAttributeDto driverAttributeDto) {
        LambdaQueryWrapper<DriverAttribute> queryWrapper = Wrappers.<DriverAttribute>query().lambda();
        if (null != driverAttributeDto) {
            if (StrUtil.isNotBlank(driverAttributeDto.getName())) {
                queryWrapper.like(DriverAttribute::getName, driverAttributeDto.getName());
            }
            if (StrUtil.isNotBlank(driverAttributeDto.getDisplayName())) {
                queryWrapper.like(DriverAttribute::getDisplayName, driverAttributeDto.getDisplayName());
            }
            if (StrUtil.isNotBlank(driverAttributeDto.getType())) {
                queryWrapper.eq(DriverAttribute::getType, driverAttributeDto.getType());
            }
            if (null != driverAttributeDto.getDriverId()) {
                queryWrapper.eq(DriverAttribute::getDriverId, driverAttributeDto.getDriverId());
            }
        }
        return queryWrapper;
    }

}

package org.industry.center.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.mapper.TenantMapper;
import org.industry.center.auth.service.TenantService;
import org.industry.common.bean.Pages;
import org.industry.common.constant.CacheConstant;
import org.industry.common.dto.TenantDto;
import org.industry.common.exception.NotFoundException;
import org.industry.common.exception.ServiceException;
import org.industry.common.model.Tenant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TenantServiceImpl implements TenantService {

    @Resource
    private TenantMapper tenantMapper;

    @Override
    @Cacheable(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.NAME, key = "#name", unless = "#result==null")
    public Tenant selectByName(String name) {
        LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.<Tenant>query().lambda();
        queryWrapper.eq(Tenant::getName, name);
        Tenant tenant = tenantMapper.selectOne(queryWrapper);
        if (null == tenant) {
            throw new NotFoundException("This tenant does not exist");
        }
        return tenant;
    }

    /**
     * 新增
     *
     * @param tenant Object
     * @return Object
     */
    @Override
    @Caching(
            put = {@CachePut(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.ID, key = "#tenant.id", condition = "#result!=null"),
                    @CachePut(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.NAME, key = "#tenant.name", condition = "#result!=null")},
            evict = {@CacheEvict(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null")}
    )
    public Tenant add(Tenant tenant) {
        Tenant select = selectByName(tenant.getName());
        if (null != select) {
            throw new ServiceException("select name already exists");
        }
        if (tenantMapper.insert(tenant) > 0) {
            return selectById(tenant.getId());
        }
        throw new ServiceException("select add failed");
    }

    /**
     * 删除
     *
     * @param id Object Id
     * @return Boolean
     */
    @Override
    @Caching(
            evict = {@CacheEvict(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.ID, key = "#id", condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.NAME, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result==true")}
    )
    public boolean delete(String id) {
        Tenant tenant = selectById(id);
        if (null != tenant) {
            throw new ServiceException("tenant doesn't exists");
        }
        return tenantMapper.deleteById(id) > 0;
    }

    /**
     * 更新
     *
     * @param tenant Object
     * @return Object
     */
    @Override
    @Caching(put = {@CachePut(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.ID, key = "#tenant.id", condition = "#result!=null"),
            @CachePut(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.NAME, key = "#tenant.name", condition = "#result!=null")},
            evict = {@CacheEvict(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null")})
    public Tenant update(Tenant tenant) {
        tenant.setName(null).setUpdateTime(null);
        if (tenantMapper.updateById(tenant) > 0) {
            Tenant select = selectById(tenant.getId());
            tenant.setName(select.getName());
            return select;
        }
        throw new ServiceException("update tenant failed");
    }

    /**
     * 通过 ID 查询
     *
     * @param id Object Id
     * @return Object
     */
    @Override
    @Cacheable(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.ID, key = "#id", unless = "#result==null")
    public Tenant selectById(String id) {
        return tenantMapper.selectById(id);
    }

    /**
     * 获取带分页、排序
     *
     * @param dto Object Dto
     * @return Page<Object>
     */
    @Override
    @Cacheable(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.LIST, keyGenerator = "commonKeyGenerator", unless = "#result==null")
    public Page<Tenant> list(TenantDto dto) {
        if (null == dto.getPage()) {
            dto.setPage(new Pages());
        }
        return tenantMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));
    }

    /**
     * 统一接口 模糊查询构造器
     *
     * @param dto Object Dto
     * @return QueryWrapper
     */
    @Override
    public LambdaQueryWrapper<Tenant> fuzzyQuery(TenantDto dto) {
        LambdaQueryWrapper<Tenant> wrapper = Wrappers.<Tenant>query().lambda();
        if (null != dto && StrUtil.isNotBlank(dto.getName())) {
            wrapper.like(Tenant::getName, dto.getName());
        }
        return wrapper;
    }
}

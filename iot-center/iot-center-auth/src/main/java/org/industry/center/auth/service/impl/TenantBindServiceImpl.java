package org.industry.center.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.mapper.TenantBindMapper;
import org.industry.center.auth.service.TenantBindService;
import org.industry.common.bean.Pages;
import org.industry.common.constant.CacheConstant;
import org.industry.common.dto.TenantBindDto;
import org.industry.common.exception.NotFoundException;
import org.industry.common.exception.ServiceException;
import org.industry.common.model.TenantBind;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Slf4j
@Service
public class TenantBindServiceImpl implements TenantBindService {

    @Resource
    private TenantBindMapper tenantBindMapper;

    /**
     * 根据 租户ID 和 关联的用户ID 查询
     *
     * @param tenantId TenantID
     * @param userId   userId
     * @return TenantBind
     */
    @Cacheable(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.TENANT_ID + CacheConstant.Suffix.USER_ID,
            key = "#tenantId+'.'+#userId", unless = "#result==null")
    @Override
    public TenantBind selectByTenantIdAndUserId(String tenantId, String userId) {
        LambdaQueryWrapper<TenantBind> queryWrapper = Wrappers.<TenantBind>query().lambda();
        queryWrapper.eq(TenantBind::getTenantId, tenantId);
        queryWrapper.eq(TenantBind::getUserId, userId);
        TenantBind tenantBind = tenantBindMapper.selectOne(queryWrapper);
        if (null == tenantBind) {
            throw new NotFoundException("The tenant bind does not exist");
        }
        return tenantBind;
    }

    /**
     * 新增
     *
     * @param tenantBind Object
     * @return Object
     */
    @Caching(put = {@CachePut(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.ID, key = "#tenantBind.id", condition = "#result!=null"),
            @CachePut(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.TENANT_ID + CacheConstant.Suffix.USER_ID, key = "#tenantBind.tenantId+'.'+#tenantBind.userId+'.'+#tenantBind.type", condition = "#result!=null")},
            evict = {@CacheEvict(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null")})
    @Override
    public TenantBind add(TenantBind tenantBind) {
        if (tenantBindMapper.insert(tenantBind) > 0) {
            return tenantBindMapper.selectById(tenantBind.getId());
        }
        throw new ServiceException("The tenant bind add failed");
    }

    /**
     * 删除
     *
     * @param id Object Id
     * @return Boolean
     */
    @Caching(evict = {@CacheEvict(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.ID, key = "#id", condition = "#result==true"),
            @CacheEvict(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.TENANT_ID + CacheConstant.Suffix.USER_ID, allEntries = true, condition = "#result==true"),
            @CacheEvict(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result==true"),
            @CacheEvict(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result==true")})
    @Override
    public boolean delete(String id) {
        selectById(id);
        return tenantBindMapper.deleteById(id) > 0;
    }

    /**
     * 更新
     *
     * @param tenantBind Object
     * @return Object
     */
    @Caching(put = {@CachePut(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.ID, key = "#tenantBind.id", condition = "#result!=null"),
            @CachePut(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.TENANT_ID + CacheConstant.Suffix.USER_ID,
                    key = "#tenantBind.tenantId+'.'+#tenantBind.userId+'.'+#tenantBind.type", condition = "#result!=null")},
            evict = {@CacheEvict(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null")}
    )
    @Override
    public TenantBind update(TenantBind tenantBind) {
        selectById(tenantBind.getId());
        tenantBind.setUpdateTime(null);
        if (tenantBindMapper.updateById(tenantBind) > 0) {
            return tenantBindMapper.selectById(tenantBind.getId());
        }
        throw new ServiceException("The tenant bind update failed");
    }

    /**
     * 通过 ID 查询
     *
     * @param id Object Id
     * @return Object
     */
    @Cacheable(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.ID, key = "#id", unless = "#result==null")
    @Override
    public TenantBind selectById(String id) {
        TenantBind tenantBind = tenantBindMapper.selectById(id);
        if (null == tenantBind) {
            throw new NotFoundException("The tenant bind does not exist");
        }
        return tenantBind;
    }

    /**
     * 获取带分页、排序
     *
     * @param tenantBindDto Object Dto
     * @return Page<Object>
     */
    @Cacheable(value = CacheConstant.Entity.TENANT_BIND + CacheConstant.Suffix.LIST, keyGenerator = "commonKeyGenerator", unless = "#result==null")
    @Override
    public Page<TenantBind> list(TenantBindDto tenantBindDto) {
        if (!Optional.ofNullable(tenantBindDto.getPage()).isPresent()) {
            tenantBindDto.setPage(new Pages());
        }
        return tenantBindMapper.selectPage(tenantBindDto.getPage().convert(), fuzzyQuery(tenantBindDto));
    }

    /**
     * 统一接口 模糊查询构造器
     *
     * @param tenantBindDto Object Dto
     * @return QueryWrapper
     */
    @Override
    public LambdaQueryWrapper<TenantBind> fuzzyQuery(TenantBindDto tenantBindDto) {
        LambdaQueryWrapper<TenantBind> queryWrapper = Wrappers.<TenantBind>query().lambda();
        if (null != tenantBindDto) {
            if (null != tenantBindDto.getTenantId()) {
                queryWrapper.eq(TenantBind::getTenantId, tenantBindDto.getTenantId());
            }
            if (null != tenantBindDto.getUserId()) {
                queryWrapper.eq(TenantBind::getUserId, tenantBindDto.getUserId());
            }
        }
        return queryWrapper;
    }
}

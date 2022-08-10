package org.industry.center.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.mapper.TenantMapper;
import org.industry.center.auth.service.TenantService;
import org.industry.common.bean.Pages;
import org.industry.common.dto.TenantDto;
import org.industry.common.exception.NotFoundException;
import org.industry.common.exception.ServiceException;
import org.industry.common.model.Tenant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TenantServiceImpl implements TenantService {

    @Resource
    private TenantMapper tenantMapper;

    @Override
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
     * @param type Object
     * @return Object
     */
    @Override
    public Tenant add(Tenant type) {
        Tenant tenant = selectByName(type.getName());
        if (null != tenant) {
            throw new ServiceException("tenant name already exists");
        }
        if (tenantMapper.insert(type) > 0) {
            return selectById(type.getId());
        }
        throw new ServiceException("tenant add failed");
    }

    /**
     * 删除
     *
     * @param id Object Id
     * @return Boolean
     */
    @Override
    public boolean delete(String id) {
        Tenant tenant = selectById(id);
        if (null != tenant) {
            throw new ServiceException("tenant does't exists");
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
        if(null != dto && StrUtil.isNotBlank(dto.getName())) {
            wrapper.like(Tenant::getName, dto.getName());
        }
        return wrapper;
    }
}

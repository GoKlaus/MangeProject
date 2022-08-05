package org.industry.center.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.mapper.TenantMapper;
import org.industry.center.auth.service.TenantService;
import org.industry.common.exception.NotFoundException;
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
}

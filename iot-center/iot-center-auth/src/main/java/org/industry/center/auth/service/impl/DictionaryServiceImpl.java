package org.industry.center.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.mapper.BlackIpMapper;
import org.industry.center.auth.mapper.TenantMapper;
import org.industry.center.auth.mapper.UserMapper;
import org.industry.center.auth.service.DictionaryService;
import org.industry.common.bean.Dictionary;
import org.industry.common.constant.CacheConstant;
import org.industry.common.model.BlackIp;
import org.industry.common.model.Tenant;
import org.industry.common.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private TenantMapper tenantMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private BlackIpMapper blackIpMapper;

    /**
     * @return
     */
    @Override
    @Cacheable(value = CacheConstant.Entity.TENANT + CacheConstant.Suffix.DIC, key = "'dic'", unless = "#result==null")
    public List<Dictionary> tenantDictionary() {
        List<Dictionary> list = Lists.newArrayListWithCapacity(16);
        LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.<Tenant>query().lambda();
        List<Tenant> tenantList = tenantMapper.selectList(queryWrapper);
        tenantList.stream().map(tenant -> new Dictionary().setLabel(tenant.getName()).setValue(tenant.getId())).forEach(list::add);
        return list;
    }

    /**
     * @return
     */
    @Override
    @Cacheable(value = CacheConstant.Entity.USER + CacheConstant.Suffix.DIC, key = "'dic.'+#tenantId", unless = "#result==null")
    public List<Dictionary> userDictionary() {
        List<Dictionary> list = Lists.newArrayListWithCapacity(16);
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>query().lambda();
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.stream().map(user -> new Dictionary().setLabel(user.getName()).setValue(user.getId())).forEach(list::add);
        return list;
    }

    /**
     * @return
     */
    @Override
    @Cacheable(value = CacheConstant.Entity.BLACK_IP + CacheConstant.Suffix.DIC, key = "'dic.'+#tenantId", unless = "#result==null")
    public List<Dictionary> blackIpDictionary() {
        List<Dictionary> list = Lists.newArrayListWithCapacity(16);
        LambdaQueryWrapper<BlackIp> queryWrapper = Wrappers.<BlackIp>query().lambda();
        List<BlackIp> blackIpList = blackIpMapper.selectList(queryWrapper);
        blackIpList.stream().map(blackIp -> new Dictionary().setLabel(blackIp.getIp()).setValue(blackIp.getId())).forEach(list::add);
        return list;
    }
}

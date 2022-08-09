package org.industry.center.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.mapper.BlackIpMapper;
import org.industry.center.auth.service.BlackIpService;
import org.industry.common.dto.BlackIpDto;
import org.industry.common.model.BlackIp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class BlackIpServiceImpl implements BlackIpService {

    @Resource
    private BlackIpMapper blackIpMapper;

    /**
     * @param ip
     * @return
     */
    @Override
    public boolean checkBlackIpValid(String ip) {
        BlackIp blackIp = selectByIp(ip);
        if (null != blackIp) {
            return blackIp.getEnable();
        }
        return false;
    }

    @Override
    public BlackIp selectByIp(String ip) {
        LambdaQueryWrapper<BlackIp> queryWrapper = Wrappers.<BlackIp>query().lambda();
        queryWrapper.eq(BlackIp::getIp, ip);
        return blackIpMapper.selectOne(queryWrapper);
    }

    /**
     * 新增
     *
     * @param type Object
     * @return Object
     */
    @Override
    public BlackIp add(BlackIp type) {
        return null;
    }

    /**
     * 删除
     *
     * @param id Object Id
     * @return Boolean
     */
    @Override
    public boolean delete(String id) {
        return false;
    }

    /**
     * 更新
     *
     * @param type Object
     * @return Object
     */
    @Override
    public BlackIp update(BlackIp type) {
        return null;
    }

    /**
     * 通过 ID 查询
     *
     * @param id Object Id
     * @return Object
     */
    @Override
    public BlackIp selectById(String id) {
        return null;
    }

    /**
     * 获取带分页、排序
     *
     * @param dto Object Dto
     * @return Page<Object>
     */
    @Override
    public Page<BlackIp> list(BlackIpDto dto) {
        return null;
    }

    /**
     * 统一接口 模糊查询构造器
     *
     * @param dto Object Dto
     * @return QueryWrapper
     */
    @Override
    public LambdaQueryWrapper<BlackIp> fuzzyQuery(BlackIpDto dto) {
        return null;
    }
}

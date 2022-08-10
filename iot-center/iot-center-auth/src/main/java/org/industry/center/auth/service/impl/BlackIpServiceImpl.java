package org.industry.center.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.mapper.BlackIpMapper;
import org.industry.center.auth.service.BlackIpService;
import org.industry.common.bean.Pages;
import org.industry.common.dto.BlackIpDto;
import org.industry.common.exception.ServiceException;
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
     * @param blackIp Object
     * @return Object
     */
    @Override
    public BlackIp add(BlackIp blackIp) {
        BlackIp select = selectByIp(blackIp.getIp());
        if (null != select) {
            throw new ServiceException("black ip already exists");
        }
        if (blackIpMapper.insert(blackIp) > 0) {
            return blackIpMapper.selectById(blackIp.getId());
        }
        throw new ServiceException("add black ip failed");
    }

    /**
     * 删除
     *
     * @param id Object Id
     * @return Boolean
     */
    @Override
    public boolean delete(String id) {
        BlackIp select = selectById(id);
        if (null == select) {
            throw new ServiceException("black ip doesn't exists");
        }
        return blackIpMapper.deleteById(id) > 0;
    }

    /**
     * 更新
     *
     * @param blackIp BlackIp
     * @return Object
     */
    @Override
    public BlackIp update(BlackIp blackIp) {
        blackIp.setIp(null).setUpdateTime(null);
        if (blackIpMapper.updateById(blackIp) > 0) {
            BlackIp select = blackIpMapper.selectById(blackIp.getId());
            blackIp.setIp(select.getIp());
            return select;
        }
        throw new ServiceException("black ip update failed");
    }

    /**
     * 通过 ID 查询
     *
     * @param id Object Id
     * @return Object
     */
    @Override
    public BlackIp selectById(String id) {
        return blackIpMapper.selectById(id);
    }

    /**
     * 获取带分页、排序
     *
     * @param dto Object Dto
     * @return Page<Object>
     */
    @Override
    public Page<BlackIp> list(BlackIpDto dto) {
        if (null == dto.getPage()) {
            dto.setPage(new Pages());
        }
        return blackIpMapper.selectPage(dto.getPage().convert(), fuzzyQuery(dto));
    }

    /**
     * 统一接口 模糊查询构造器
     *
     * @param dto Object Dto
     * @return QueryWrapper
     */
    @Override
    public LambdaQueryWrapper<BlackIp> fuzzyQuery(BlackIpDto dto) {
        LambdaQueryWrapper<BlackIp> wrapper = Wrappers.<BlackIp>query().lambda();

        if (null != dto && StrUtil.isNotBlank(dto.getIp())) {
            wrapper.like(BlackIp::getIp, dto.getIp());

        }
        return wrapper;
    }
}

package org.industry.center.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.center.manager.mapper.PointMapper;
import org.industry.center.manager.service.PointService;
import org.industry.center.manager.service.ProfileBindService;
import org.industry.common.bean.Pages;
import org.industry.common.constant.CacheConstant;
import org.industry.common.dto.PointDto;
import org.industry.common.exception.DuplicateException;
import org.industry.common.exception.NotFoundException;
import org.industry.common.exception.ServiceException;
import org.industry.common.model.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * PointService Impl
 *
 * @author pnoker
 */
@Slf4j
@Service
public class PointServiceImpl implements PointService {

    @Resource
    private PointMapper pointMapper;
    @Resource
    private ProfileBindService profileBindService;

    @Override
    @Caching(
            put = {
                    @CachePut(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.ID, key = "#point.id", condition = "#result!=null"),
                    @CachePut(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.NAME + CacheConstant.Suffix.PROFILE_ID, key = "#point.name+'.'+#point.profileId", condition = "#result!=null")
            },
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.PROFILE_ID + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.UNIT, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE + CacheConstant.Suffix.ID, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE + CacheConstant.Suffix.NAME, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null")
            }
    )
    public Point add(Point point) {
        try {
            selectByNameAndProfileId(point.getName(), point.getProfileId());
            throw new DuplicateException("The point already exists in the profile");
        } catch (NotFoundException notFoundException) {
            if (pointMapper.insert(point) > 0) {
                return pointMapper.selectById(point.getId());
            }
            throw new ServiceException("The point add failed");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.ID, key = "#id", condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.NAME + CacheConstant.Suffix.PROFILE_ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.PROFILE_ID + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.UNIT, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE + CacheConstant.Suffix.ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE + CacheConstant.Suffix.NAME, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result==true")
            }
    )
    public boolean delete(String id) {
        selectById(id);
        return pointMapper.deleteById(id) > 0;
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.ID, key = "#point.id", condition = "#result!=null"),
                    @CachePut(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.NAME + CacheConstant.Suffix.PROFILE_ID, key = "#point.name+'.'+#point.profileId", condition = "#result!=null")
            },
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.PROFILE_ID + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.UNIT, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE + CacheConstant.Suffix.ID, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE + CacheConstant.Suffix.NAME, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null")
            }
    )
    public Point update(Point point) {
        Point old = selectById(point.getId());
        point.setUpdateTime(null);
        if (!old.getProfileId().equals(point.getProfileId()) || !old.getName().equals(point.getName())) {
            try {
                selectByNameAndProfileId(point.getName(), point.getProfileId());
                throw new DuplicateException("The point already exists");
            } catch (NotFoundException ignored) {
            }
        }
        if (pointMapper.updateById(point) > 0) {
            Point select = pointMapper.selectById(point.getId());
            point.setName(select.getName()).setProfileId(select.getProfileId());
            return select;
        }
        throw new ServiceException("The point update failed");
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.ID, key = "#id", unless = "#result==null")
    public Point selectById(String id) {
        Point point = pointMapper.selectById(id);
        if (null == point) {
            throw new NotFoundException("The point does not exist");
        }
        return point;
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.NAME + CacheConstant.Suffix.PROFILE_ID, key = "#name+'.'+#profileId", unless = "#result==null")
    public Point selectByNameAndProfileId(String name, String profileId) {
        LambdaQueryWrapper<Point> queryWrapper = Wrappers.<Point>query().lambda();
        queryWrapper.eq(Point::getName, name);
        queryWrapper.eq(Point::getProfileId, profileId);
        Point point = pointMapper.selectOne(queryWrapper);
        if (null == point) {
            throw new NotFoundException("The point does not exist");
        }
        return point;
    }

    @Override
    public List<Point> selectByDeviceId(String deviceId) {
        Set<String> profileIds = profileBindService.selectProfileIdByDeviceId(deviceId);
        return selectByProfileIds(profileIds);
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.PROFILE_ID + CacheConstant.Suffix.LIST, key = "#profileId", unless = "#result==null")
    public List<Point> selectByProfileId(String profileId) {
        PointDto pointDto = new PointDto();
        pointDto.setProfileId(profileId);
        List<Point> points = pointMapper.selectList(fuzzyQuery(pointDto));
        if (null == points || points.size() < 1) {
            throw new NotFoundException("The points does not exist");
        }
        return points;
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.LIST, keyGenerator = "commonKeyGenerator", unless = "#result==null")
    public List<Point> selectByProfileIds(Set<String> profileIds) {
        List<Point> points = new ArrayList<>(16);
        profileIds.forEach(profileId -> {
            PointDto pointDto = new PointDto();
            pointDto.setProfileId(profileId);
            List<Point> pointList = pointMapper.selectList(fuzzyQuery(pointDto));
            if (null != pointList) {
                points.addAll(pointList);
            }
        });
        if (points.size() < 1) {
            throw new NotFoundException("The points does not exist");
        }
        return points;
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.LIST, keyGenerator = "commonKeyGenerator", unless = "#result==null")
    public Page<Point> list(PointDto pointDto) {
        if (!Optional.ofNullable(pointDto.getPage()).isPresent()) {
            pointDto.setPage(new Pages());
        }
        return pointMapper.selectPage(pointDto.getPage().convert(), fuzzyQuery(pointDto));
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.POINT + CacheConstant.Suffix.UNIT, keyGenerator = "commonKeyGenerator", unless = "#result==null")
    public Map<String, String> unit(Set<String> pointIds) {
        List<Point> points = pointMapper.selectBatchIds(pointIds);
        return points.stream().collect(Collectors.toMap(Point::getId, Point::getUnit));
    }

    @Override
    public LambdaQueryWrapper<Point> fuzzyQuery(PointDto pointDto) {
        LambdaQueryWrapper<Point> queryWrapper = Wrappers.<Point>query().lambda();
        if (null != pointDto) {
            if (StrUtil.isNotBlank(pointDto.getName())) {
                queryWrapper.like(Point::getName, pointDto.getName());
            }
            if (StrUtil.isNotBlank(pointDto.getType())) {
                queryWrapper.eq(Point::getType, pointDto.getType());
            }
            if (null != pointDto.getRw()) {
                queryWrapper.eq(Point::getRw, pointDto.getRw());
            }
            if (null != pointDto.getAccrue()) {
                queryWrapper.eq(Point::getAccrue, pointDto.getAccrue());
            }
            if (null != pointDto.getProfileId()) {
                queryWrapper.eq(Point::getProfileId, pointDto.getProfileId());
            }
            if (null != pointDto.getEnable()) {
                queryWrapper.eq(Point::getEnable, pointDto.getEnable());
            }
            if (null != pointDto.getTenantId()) {
                queryWrapper.eq(Point::getTenantId, pointDto.getTenantId());
            }
        }
        return queryWrapper;
    }

}

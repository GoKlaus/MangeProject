package org.industry.center.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.center.manager.mapper.ProfileBindMapper;
import org.industry.center.manager.mapper.ProfileMapper;
import org.industry.center.manager.service.ProfileBindService;
import org.industry.common.bean.Pages;
import org.industry.common.constant.CacheConstant;
import org.industry.common.dto.ProfileBindDto;
import org.industry.common.exception.DuplicateException;
import org.industry.common.exception.NotFoundException;
import org.industry.common.exception.ServiceException;
import org.industry.common.model.Profile;
import org.industry.common.model.ProfileBind;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ProfileBindService Impl
 *
 * @author pnoker
 */
@Slf4j
@Service
public class ProfileBindServiceImpl implements ProfileBindService {

    @Resource
    private ProfileMapper profileMapper;
    @Resource
    private ProfileBindMapper profileBindMapper;

    @Override
    @Caching(
            put = {@CachePut(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.ID, key = "#profileBind.id", condition = "#result!=null")},
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.PROFILE_ID, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DEVICE_ID, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DEVICE_ID + CacheConstant.Suffix.PROFILE_ID, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null")
            }
    )
    public ProfileBind add(ProfileBind profileBind) {
        try {
            selectByDeviceIdAndProfileId(profileBind.getDeviceId(), profileBind.getProfileId());
            throw new DuplicateException("The profile bind already exists");
        } catch (NotFoundException notFoundException) {
            if (profileBindMapper.insert(profileBind) > 0) {
                return profileBindMapper.selectById(profileBind.getId());
            }
            throw new ServiceException("The profile bind add failed");
        }
    }

    @Override
    public List<ProfileBind> addByDeviceId(String deviceId, Set<String> profileIds) {
        List<ProfileBind> profileBinds = new ArrayList<>();
        if (null != profileIds) {
            profileIds.forEach(profileId -> {
                Profile profile = profileMapper.selectById(profileId);
                if (ObjectUtil.isNotNull(profile)) {
                    ProfileBind profileBind = add(new ProfileBind(profileId, deviceId));
                    profileBinds.add(profileBind);
                }
            });
        }
        return profileBinds;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.ID, key = "#id", condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.PROFILE_ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DEVICE_ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DEVICE_ID + CacheConstant.Suffix.PROFILE_ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result==true")
            }
    )
    public boolean delete(String id) {
        selectById(id);
        return profileBindMapper.deleteById(id) > 0;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.PROFILE_ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DEVICE_ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DEVICE_ID + CacheConstant.Suffix.PROFILE_ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result==true")
            }
    )
    public boolean deleteByDeviceId(String deviceId) {
        ProfileBindDto profileBindDto = new ProfileBindDto();
        profileBindDto.setDeviceId(deviceId);
        return profileBindMapper.delete(fuzzyQuery(profileBindDto)) > 0;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.PROFILE_ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DEVICE_ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DEVICE_ID + CacheConstant.Suffix.PROFILE_ID, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result==true"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result==true")
            }
    )
    public boolean deleteByProfileIdAndDeviceId(String deviceId, String profileId) {
        ProfileBindDto profileBindDto = new ProfileBindDto();
        profileBindDto.setProfileId(profileId);
        profileBindDto.setDeviceId(deviceId);
        return profileBindMapper.delete(fuzzyQuery(profileBindDto)) > 0;
    }

    @Override
    @Caching(
            put = {@CachePut(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.ID, key = "#profileBind.id", condition = "#result!=null")},
            evict = {
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.PROFILE_ID, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DEVICE_ID, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DEVICE_ID + CacheConstant.Suffix.PROFILE_ID, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DIC, allEntries = true, condition = "#result!=null"),
                    @CacheEvict(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.LIST, allEntries = true, condition = "#result!=null")
            }
    )
    public ProfileBind update(ProfileBind profileBind) {
        selectById(profileBind.getId());
        profileBind.setUpdateTime(null);
        if (profileBindMapper.updateById(profileBind) > 0) {
            return profileBindMapper.selectById(profileBind.getId());
        }
        throw new ServiceException("The profile bind update failed");
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.ID, key = "#id", unless = "#result==null")
    public ProfileBind selectById(String id) {
        ProfileBind profileBind = profileBindMapper.selectById(id);
        if (null == profileBind) {
            throw new NotFoundException("The profile bind does not exist");
        }
        return profileBind;
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DEVICE_ID + CacheConstant.Suffix.PROFILE_ID, key = "#deviceId+'.'+#profileId", unless = "#result==null")
    public ProfileBind selectByDeviceIdAndProfileId(String deviceId, String profileId) {
        ProfileBindDto profileBindDto = new ProfileBindDto();
        profileBindDto.setDeviceId(deviceId);
        profileBindDto.setProfileId(profileId);
        ProfileBind profileBind = profileBindMapper.selectOne(fuzzyQuery(profileBindDto));
        if (null == profileBind) {
            throw new NotFoundException("The profile bind does not exist");
        }
        return profileBind;
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.PROFILE_ID, key = "#profileId", unless = "#result==null")
    public Set<String> selectDeviceIdByProfileId(String profileId) {
        ProfileBindDto profileBindDto = new ProfileBindDto();
        profileBindDto.setProfileId(profileId);
        List<ProfileBind> profileBinds = profileBindMapper.selectList(fuzzyQuery(profileBindDto));
        return profileBinds.stream().map(ProfileBind::getDeviceId).collect(Collectors.toSet());
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.DEVICE_ID, key = "#deviceId", unless = "#result==null")
    public Set<String> selectProfileIdByDeviceId(String deviceId) {
        ProfileBindDto profileBindDto = new ProfileBindDto();
        profileBindDto.setDeviceId(deviceId);
        List<ProfileBind> profileBinds = profileBindMapper.selectList(fuzzyQuery(profileBindDto));
        return profileBinds.stream().map(ProfileBind::getProfileId).collect(Collectors.toSet());
    }

    @Override
    @Cacheable(value = CacheConstant.Entity.PROFILE_BIND + CacheConstant.Suffix.LIST, keyGenerator = "commonKeyGenerator", unless = "#result==null")
    public Page<ProfileBind> list(ProfileBindDto profileBindDto) {
        if (!Optional.ofNullable(profileBindDto.getPage()).isPresent()) {
            profileBindDto.setPage(new Pages());
        }
        return profileBindMapper.selectPage(profileBindDto.getPage().convert(), fuzzyQuery(profileBindDto));
    }

    @Override
    public LambdaQueryWrapper<ProfileBind> fuzzyQuery(ProfileBindDto profileBindDto) {
        LambdaQueryWrapper<ProfileBind> queryWrapper = Wrappers.<ProfileBind>query().lambda();
        if (null != profileBindDto) {
            if (null != profileBindDto.getProfileId()) {
                queryWrapper.eq(ProfileBind::getProfileId, profileBindDto.getProfileId());
            }
            if (null != profileBindDto.getDeviceId()) {
                queryWrapper.eq(ProfileBind::getDeviceId, profileBindDto.getDeviceId());
            }
        }
        return queryWrapper;
    }

}

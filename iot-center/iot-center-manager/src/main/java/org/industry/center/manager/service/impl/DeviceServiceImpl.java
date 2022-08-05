package org.industry.center.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.manager.service.DeviceService;
import org.industry.common.dto.DeviceDto;
import org.industry.common.model.Device;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

    @Override
    public Device selectByName(String name, String tenantId) {
        return null;
    }

    @Override
    public List<Device> selectByDriverId(String driverId) {
        return null;
    }

    @Override
    public List<Device> selectByProfileId(String profileId) {
        return null;
    }

    @Override
    public List<Device> selectByIds(Set<String> ids) {
        return null;
    }

    @Override
    public Device add(Device type) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Device update(Device type) {
        return null;
    }

    @Override
    public Device selectById(String id) {
        return null;
    }

    @Override
    public Page<Device> list(DeviceDto dto) {
        return null;
    }

    @Override
    public LambdaQueryWrapper<Device> fuzzyQuery(DeviceDto dto) {
        return null;
    }
}

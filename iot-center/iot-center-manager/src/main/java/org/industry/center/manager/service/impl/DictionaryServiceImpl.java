package org.industry.center.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.manager.mapper.DeviceMapper;
import org.industry.center.manager.mapper.DriverMapper;
import org.industry.center.manager.mapper.ProfileMapper;
import org.industry.center.manager.service.DictionaryService;
import org.industry.common.bean.Dictionary;
import org.industry.common.model.Device;
import org.industry.common.model.Driver;
import org.industry.common.model.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DriverMapper driverMapper;
    @Resource
    private DeviceMapper deviceMapper;

    @Resource
    private ProfileMapper profileMapper;

    @Override
    public List<Dictionary> deviceDictionary(String tenantId) {
        List<Dictionary> dictionaries = driverDictionary(tenantId);
        dictionaries.forEach(driverDictionary -> {
            LambdaQueryWrapper<Device> queryWrapper = Wrappers.<Device>query().lambda();
            queryWrapper.eq(Device::getDriverId, driverDictionary.getValue());
            queryWrapper.eq(Device::getTenantId, tenantId);
            List<Device> deviceList = deviceMapper.selectList(queryWrapper);
            List<Dictionary> deviceDictionaryList = new ArrayList<>(16);
            deviceList.forEach(device -> deviceDictionaryList.add(new Dictionary().setLabel(device.getName()).setValue(device.getId())));
            driverDictionary.setChildren(deviceDictionaryList);
        });

        return dictionaries;
    }

    @Override
    public List<Dictionary> driverDictionary(String tenantId) {
        List<Dictionary> dictionaries = new ArrayList<>(16);
        LambdaQueryWrapper<Driver> queryWrapper = Wrappers.<Driver>query().lambda();
        queryWrapper.eq(Driver::getTenantId, tenantId);
        List<Driver> drivers = driverMapper.selectList(queryWrapper);
        drivers.forEach(driver -> dictionaries.add(new Dictionary().setLabel(driver.getName()).setValue(driver.getId())));
        return dictionaries;
    }

    @Override
    public List<Dictionary> profileDictionary(String tenantId) {
        List<Dictionary> dictionaries = new ArrayList<>(16);
        LambdaQueryWrapper<Profile> queryWrapper = Wrappers.<Profile>query().lambda();
        queryWrapper.eq(Profile::getTenantId, tenantId);
        List<Profile> profiles = profileMapper.selectList(queryWrapper);
        profiles.forEach(profile -> dictionaries.add(new Dictionary().setLabel(profile.getName()).setValue(profile.getId())));
        return dictionaries;
    }
}

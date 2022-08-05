package org.industry.center.manager.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.api.manager.feign.DeviceClient;
import org.industry.center.manager.service.DeviceService;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.DeviceDto;
import org.industry.common.model.Device;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = ServiceConstant.Manager.DEVICE_URL_PREFIX)
public class DeviceApi implements DeviceClient {

    @Resource
    private DeviceService deviceService;

    @Override
    public R<Device> add(Device device, String tenantId) {
        return null;
    }

    @Override
    public R<Boolean> delete(String id) {
        return null;
    }

    @Override
    public R<Device> update(Device device, String tenantId) {
        return null;
    }

    @Override
    public R<Device> selectById(String id) {
        return null;
    }

    @Override
    public R<List<Device>> selectByDriverId(String driverId) {
        return null;
    }

    @Override
    public R<List<Device>> selectByProfileId(String profileId) {
        return null;
    }

    @Override
    public R<Page<Device>> list(DeviceDto deviceDto, String tenantId) {
        try {
            deviceDto.setTenantId(tenantId);
            Page<Device> page = deviceService.list(deviceDto);
            if (null != page) {
                return R.ok(page);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }
}

package org.industry.center.manager.api;

import org.industry.api.manager.feign.StatusClient;
import org.industry.center.manager.service.StatusService;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.DeviceDto;
import org.industry.common.dto.DriverDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 设备 Client 接口实现
 *
 * @author pnoker
 */
@Slf4j
@RestController
@RequestMapping(ServiceConstant.Manager.STATUS_URL_PREFIX)
public class StatusApi implements StatusClient {

    @Resource
    private StatusService statusService;

    @Override
    public R<Map<String, String>> driverStatus(DriverDto driverDto, String tenantId) {
        try {
            driverDto.setTenantId(tenantId);
            Map<String, String> statuses = statusService.driver(driverDto);
            return R.ok(statuses);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<Map<String, String>> deviceStatus(DeviceDto deviceDto, String tenantId) {
        try {
            deviceDto.setTenantId(tenantId);
            Map<String, String> statuses = statusService.device(deviceDto);
            return R.ok(statuses);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<Map<String, String>> deviceStatusByDriverId(String driverId) {
        try {
            DeviceDto deviceDto = new DeviceDto();
            deviceDto.setDriverId(driverId);
            Map<String, String> statuses = statusService.device(deviceDto);
            return R.ok(statuses);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @Override
    public R<Map<String, String>> deviceStatusByProfileId(String profileId) {
        try {
            Map<String, String> statuses = statusService.deviceByProfileId(profileId);
            return R.ok(statuses);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

}

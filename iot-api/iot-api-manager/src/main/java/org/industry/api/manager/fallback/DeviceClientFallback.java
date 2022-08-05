package org.industry.api.manager.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.api.manager.feign.DeviceClient;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.DeviceDto;
import org.industry.common.model.Device;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DeviceClientFallback implements FallbackFactory<DeviceClient> {
    @Override
    public DeviceClient create(Throwable cause) {
        // 错误原因日志
        String message = cause.getMessage() == null ? "No available server for client: " + ServiceConstant.Manager.SERVICE_NAME : cause.getMessage();
        log.error("Fallback: {}", message);
        return new DeviceClient() {
            @Override
            public R<Device> add(Device device, String tenantId) {
                return R.fail(message);
            }

            @Override
            public R<Boolean> delete(String id) {
                return R.fail(message);
            }

            @Override
            public R<Device> update(Device device, String tenantId) {
                return R.fail(message);
            }

            @Override
            public R<Device> selectById(String id) {
                return R.fail(message);
            }

            @Override
            public R<List<Device>> selectByDriverId(String driverId) {
                return R.fail(message);
            }

            @Override
            public R<List<Device>> selectByProfileId(String profileId) {
                return R.fail(message);
            }

            @Override
            public R<Page<Device>> list(DeviceDto deviceDto, String tenantId) {
                return R.fail(message);
            }
        };
    }
}

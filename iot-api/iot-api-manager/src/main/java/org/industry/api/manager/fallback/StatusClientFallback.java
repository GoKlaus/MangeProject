package org.industry.api.manager.fallback;

import lombok.extern.slf4j.Slf4j;
import org.industry.api.manager.feign.StatusClient;
import org.industry.common.bean.R;
import org.industry.common.dto.DeviceDto;
import org.industry.common.dto.DriverDto;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * DeviceClientFallback
 *
 * @author pnoker
 */
@Slf4j
@Component
public class StatusClientFallback implements FallbackFactory<StatusClient> {

    @Override
    public StatusClient create(Throwable throwable) {
        String message = throwable.getMessage() == null ? "No available server for client: DC3-CENTER-MANAGER" : throwable.getMessage();
        log.error("Fallback:{}", message);

        return new StatusClient() {

            @Override
            public R<Map<String, String>> driverStatus(DriverDto driverDto, String tenantId) {
                return R.fail(message);
            }

            @Override
            public R<Map<String, String>> deviceStatus(DeviceDto deviceDto, String tenantId) {
                return R.fail(message);
            }

            @Override
            public R<Map<String, String>> deviceStatusByDriverId(String driverId) {
                return R.fail(message);
            }

            @Override
            public R<Map<String, String>> deviceStatusByProfileId(String profileId) {
                return R.fail(message);
            }
        };
    }
}
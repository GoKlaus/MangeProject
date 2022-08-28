package org.industry.api.manager.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.api.manager.feign.DriverInfoClient;
import org.industry.common.bean.R;
import org.industry.common.dto.DriverInfoDto;
import org.industry.common.model.DriverInfo;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * DriverInfoClientFallback
 *
 * @author pnoker
 */
@Slf4j
@Component
public class DriverInfoClientFallback implements FallbackFactory<DriverInfoClient> {

    @Override
    public DriverInfoClient create(Throwable throwable) {
        String message = throwable.getMessage() == null ? "No available server for client: DC3-CENTER-MANAGER" : throwable.getMessage();
        log.error("Fallback:{}", message);

        return new DriverInfoClient() {

            @Override
            public R<DriverInfo> add(DriverInfo driverInfo) {
                return R.fail(message);
            }

            @Override
            public R<Boolean> delete(String id) {
                return R.fail(message);
            }

            @Override
            public R<DriverInfo> update(DriverInfo driverInfo) {
                return R.fail(message);
            }

            @Override
            public R<DriverInfo> selectById(String id) {
                return R.fail(message);
            }

            @Override
            public R<DriverInfo> selectByAttributeIdAndDeviceId(String attributeId, String deviceId) {
                return R.fail(message);
            }

            @Override
            public R<List<DriverInfo>> selectByDeviceId(String deviceId) {
                return R.fail(message);
            }

            @Override
            public R<Page<DriverInfo>> list(DriverInfoDto driverInfoDto) {
                return R.fail(message);
            }

        };
    }
}
package org.industry.api.manager.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.api.manager.feign.DriverAttributeClient;
import org.industry.common.bean.R;
import org.industry.common.dto.DriverAttributeDto;
import org.industry.common.model.DriverAttribute;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * DriverAttributeClient
 *
 * @author pnoker
 */
@Slf4j
@Component
public class DriverAttributeClientFallback implements FallbackFactory<DriverAttributeClient> {

    @Override
    public DriverAttributeClient create(Throwable throwable) {
        String message = throwable.getMessage() == null ? "No available server for client: DC3-CENTER-MANAGER" : throwable.getMessage();
        log.error("Fallback:{}", message);

        return new DriverAttributeClient() {

            @Override
            public R<DriverAttribute> add(DriverAttribute driverAttribute) {
                return R.fail(message);
            }

            @Override
            public R<Boolean> delete(String id) {
                return R.fail(message);
            }

            @Override
            public R<DriverAttribute> update(DriverAttribute driverAttribute) {
                return R.fail(message);
            }

            @Override
            public R<DriverAttribute> selectById(String id) {
                return R.fail(message);
            }

            @Override
            public R<List<DriverAttribute>> selectByDriverId(String id) {
                return R.fail(message);
            }

            @Override
            public R<Page<DriverAttribute>> list(DriverAttributeDto driverAttributeDto) {
                return R.fail(message);
            }

        };
    }
}
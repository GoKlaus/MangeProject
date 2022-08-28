package org.industry.api.manager.fallback;

import lombok.extern.slf4j.Slf4j;
import org.industry.api.manager.feign.AutoClient;
import org.industry.common.bean.R;
import org.industry.common.bean.point.PointDetail;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * AutoClientFallback
 *
 * @author pnoker
 */
@Slf4j
@Component
public class AutoClientFallback implements FallbackFactory<AutoClient> {

    @Override
    public AutoClient create(Throwable throwable) {
        String message = throwable.getMessage() == null ? "No available server for client: DC3-CENTER-MANAGER" : throwable.getMessage();
        log.error("Fallback:{}", message);

        return new AutoClient() {

            @Override
            public R<PointDetail> autoCreateDeviceAndPoint(PointDetail pointDetail, String tenantId) {
                return R.fail(message);
            }
        };
    }
}
package org.industry.api.data.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.api.data.feign.PointValueClient;
import org.industry.common.bean.R;
import org.industry.common.bean.point.PointValue;
import org.industry.common.dto.PointValueDto;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * PointValueClientFallback
 *
 */
@Slf4j
@Component
public class PointValueClientFallback implements FallbackFactory<PointValueClient> {

    @Override
    public PointValueClient create(Throwable throwable) {
        String message = throwable.getMessage() == null ? "No available server for client: IOT-CENTER-DATA" : throwable.getMessage();
        log.error("Fallback:{}", message);

        return new PointValueClient() {

            @Override
            public R<List<PointValue>> latest(String deviceId, Boolean history) {
                return R.fail(message);
            }

            @Override
            public R<PointValue> latest(String deviceId, String pointId, Boolean history) {
                return R.fail(message);
            }

            @Override
            public R<Page<PointValue>> list(PointValueDto pointValueDto) {
                return R.fail(message);
            }

        };
    }
}
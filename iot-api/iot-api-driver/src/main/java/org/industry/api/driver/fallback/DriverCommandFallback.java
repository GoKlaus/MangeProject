package org.industry.api.driver.fallback;

import lombok.extern.slf4j.Slf4j;
import org.industry.api.driver.feign.DriverCommandClient;
import org.industry.common.bean.R;
import org.industry.common.bean.driver.command.CmdParameter;
import org.industry.common.bean.point.PointValue;
import org.industry.common.valid.ValidatableList;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DriverCommandFallback implements FallbackFactory<DriverCommandClient> {
    /**
     * @param throwable
     * @return
     */
    @Override
    public DriverCommandClient create(Throwable throwable) {
        String message = throwable.getMessage() == null ? "No available server for client: IOT-CENTER-DRIVER" : throwable.getMessage();
        log.error("Fallback:{}", message);
        return new DriverCommandClient() {
            /**
             * 读
             *
             * @param cmdParameters list<{deviceId,pointId}>
             * @return R<List < PointValue>>
             */
            @Override
            public R<List<PointValue>> readPoint(ValidatableList<CmdParameter> cmdParameters) {
                return R.fail(message);
            }

            /**
             * 写
             *
             * @param cmdParameters list<{deviceId,pointId,stringValue}>
             * @return R<Boolean>
             */
            @Override
            public R<Boolean> writePoint(ValidatableList<CmdParameter> cmdParameters) {
                return R.fail(message);
            }
        };
    }
}

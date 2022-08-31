package org.industry.api.driver.feign;

import org.industry.api.driver.fallback.DriverCommandFallback;
import org.industry.common.bean.R;
import org.industry.common.bean.driver.command.CmdParameter;
import org.industry.common.bean.point.PointValue;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.valid.Read;
import org.industry.common.valid.ValidatableList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(path = ServiceConstant.Driver.COMMAND_URL_PREFIX, fallbackFactory = DriverCommandFallback.class)
public interface DriverCommandClient {

    /**
     * 读
     *
     * @param cmdParameters list<{deviceId,pointId}>
     * @return R<List < PointValue>>
     */
    @PostMapping("/read")
    public R<List<PointValue>> readPoint(@Validated(Read.class) @RequestBody ValidatableList<CmdParameter> cmdParameters);

    /**
     * 写
     *
     * @param cmdParameters list<{deviceId,pointId,stringValue}>
     * @return R<Boolean>
     */
    @PostMapping("/write")
    public R<Boolean> writePoint(@Validated(Read.class) @RequestBody ValidatableList<CmdParameter> cmdParameters);
}

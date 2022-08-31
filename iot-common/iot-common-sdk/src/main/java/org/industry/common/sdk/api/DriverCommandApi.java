package org.industry.common.sdk.api;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.industry.api.driver.feign.DriverCommandClient;
import org.industry.common.bean.R;
import org.industry.common.bean.driver.command.CmdParameter;
import org.industry.common.bean.point.PointValue;
import org.industry.common.constant.CommonConstant;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.sdk.service.DriverCommandService;
import org.industry.common.valid.ValidatableList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(ServiceConstant.Driver.COMMAND_URL_PREFIX)
public class DriverCommandApi implements DriverCommandClient {

    @Resource
    private DriverCommandService driverCommandService;


    /**
     * 读
     *
     * @param cmdParameters list<{deviceId,pointId}>
     * @return R<List < PointValue>>
     */
    @Override
    public R<List<PointValue>> readPoint(ValidatableList<CmdParameter> cmdParameters) {
        List<PointValue> result = Lists.newArrayList();
        try {
            if (cmdParameters.size() > CommonConstant.Driver.DEFAULT_MAX_REQUEST_SIZE) {
                return R.fail("point request size are limited to " + CommonConstant.Driver.DEFAULT_MAX_REQUEST_SIZE);
            }
            cmdParameters.forEach(parameter -> {
                PointValue pointValue = driverCommandService.read(parameter.getDeviceId(), parameter.getPointId());
                Optional.ofNullable(pointValue).ifPresent(result::add);
            });

        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return null;
    }

    /**
     * 写
     *
     * @param cmdParameters list<{deviceId,pointId,stringValue}>
     * @return R<Boolean>
     */
    @Override
    public R<Boolean> writePoint(ValidatableList<CmdParameter> cmdParameters) {
        return null;
    }
}

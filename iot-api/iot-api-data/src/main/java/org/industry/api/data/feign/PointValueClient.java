
package org.industry.api.data.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.api.data.fallback.PointValueClientFallback;
import org.industry.common.bean.R;
import org.industry.common.bean.point.PointValue;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.PointValueDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 数据 FeignClient
 */
@FeignClient(path = ServiceConstant.Data.VALUE_URL_PREFIX, name = ServiceConstant.Data.SERVICE_NAME, fallbackFactory = PointValueClientFallback.class)
public interface PointValueClient {

    /**
     * 查询最新 PointValue 集合
     *
     * @param deviceId Device Id
     * @return PointValue Array
     */
    @GetMapping("/latest/device_id/{deviceId}")
    R<List<PointValue>> latest(@NotNull @PathVariable(value = "deviceId") String deviceId,
                               @RequestParam(required = false, defaultValue = "false") Boolean history);

    /**
     * 查询最新 PointValue
     *
     * @param deviceId Device Id
     * @param pointId  Point Id
     * @return PointValue
     */
    @GetMapping("/latest/device_id/{deviceId}/point_id/{pointId}")
    R<PointValue> latest(@NotNull @PathVariable(value = "deviceId") String deviceId,
                         @NotNull @PathVariable(value = "pointId") String pointId,
                         @RequestParam(required = false, defaultValue = "false") Boolean history);

    /**
     * 分页查询 PointValue
     *
     * @param pointValueDto PointValueDto
     * @return Page<PointValue>
     */
    @PostMapping("/list")
    R<Page<PointValue>> list(@RequestBody(required = false) PointValueDto pointValueDto);
}

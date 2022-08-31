package org.industry.api.rtmp.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.api.rtmp.fallback.RtmpFallback;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.RtmpDto;
import org.industry.common.model.Rtmp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@FeignClient(path = ServiceConstant.Rtmp.URL_PREFIX, fallbackFactory = RtmpFallback.class)
public interface RtmpClient {

    /**
     * 新增 Rtmp
     *
     * @param rtmp Rtmp
     * @return R<Rtmp>
     */
    @PostMapping("/add")
    R<Rtmp> add(@Validated @RequestBody Rtmp rtmp, @RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 根据 ID 删除 Rtmp
     *
     * @param id rtmpId
     * @return R<Boolean>
     */
    @PostMapping("/delete/{id}")
    R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改 Rtmp
     *
     * @param rtmp Rtmp
     * @return R<Rtmp>
     */
    @PostMapping("/update")
    R<Rtmp> update(@RequestBody Rtmp rtmp, @RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 根据 ID 查询 Rtmp
     *
     * @param id Rtmp Id
     * @return R<Rtmp>
     */
    @GetMapping("/id/{id}")
    R<Rtmp> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询 Rtmp
     *
     * @param rtmpDto Rtmp Dto
     * @return R<Page < Rtmp>>
     */
    @PostMapping("/list")
    R<Page<Rtmp>> list(@RequestBody(required = false) RtmpDto rtmpDto, @RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 启动 Rtmp 转码任务
     *
     * @param id Rtmp Id
     * @return R<Boolean>
     */
    @PostMapping("/start/{id}")
    R<Boolean> start(@NotNull @PathVariable(value = "id") String id);

    /**
     * 停止 Rtmp 转码任务
     *
     * @param id Rtmp Id
     * @return R<Boolean>
     */
    @PostMapping("/stop/{id}")
    R<Boolean> stop(@NotNull @PathVariable(value = "id") String id);
}

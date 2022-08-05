package org.industry.api.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.api.manager.fallback.DeviceClientFallback;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.DeviceDto;
import org.industry.common.model.Device;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@FeignClient(path = ServiceConstant.Manager.DEVICE_URL_PREFIX, name = ServiceConstant.Manager.SERVICE_NAME, fallbackFactory = DeviceClientFallback.class)
public interface DeviceClient {
    /**
     * 新增 Device
     *
     * @param device Device
     * @return R<Device>
     */
    @PostMapping("/add")
    R<Device> add(@Validated(Insert.class) @RequestBody Device device, @RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 根据 ID 删除 Device
     *
     * @param id Device Id
     * @return R<Boolean>
     */
    @PostMapping("/delete/{id}")
    R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改 Device
     *
     * @param device Device
     * @return R<Device>
     */
    @PostMapping("/update")
    R<Device> update(@Validated(Update.class) @RequestBody Device device, @RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 根据 ID 查询 Device
     *
     * @param id Device Id
     * @return R<Device>
     */
    @GetMapping("/id/{id}")
    R<Device> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 根据 驱动ID 查询 Device
     *
     * @param driverId Driver Id
     * @return R<Device>
     */
    @GetMapping("/driver_id/{driverId}")
    R<List<Device>> selectByDriverId(@NotNull @PathVariable(value = "driverId") String driverId);

    /**
     * 根据 模板ID 查询 Device
     *
     * @param profileId Profile Id
     * @return R<Device>
     */
    @GetMapping("/profile_id/{profileId}")
    R<List<Device>> selectByProfileId(@NotNull @PathVariable(value = "profileId") String profileId);

    /**
     * 分页查询 Device
     *
     * @param deviceDto Device Dto
     * @return R<Page < Device>>
     */
    @PostMapping("/list")
    R<Page<Device>> list(@RequestBody(required = false) DeviceDto deviceDto, @RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);
}

package org.industry.api.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.api.manager.fallback.DriverAttributeClientFallback;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.DriverAttributeDto;
import org.industry.common.model.DriverAttribute;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 驱动配置属性 FeignClient
 *
 * @author pnoker
 */
@FeignClient(path = ServiceConstant.Manager.DRIVER_ATTRIBUTE_URL_PREFIX, name = ServiceConstant.Manager.SERVICE_NAME, fallbackFactory = DriverAttributeClientFallback.class)
public interface DriverAttributeClient {

    /**
     * 新增 DriverAttribute
     *
     * @param driverAttribute DriverAttribute
     * @return DriverAttribute
     */
    @PostMapping("/add")
    R<DriverAttribute> add(@Validated(Insert.class) @RequestBody DriverAttribute driverAttribute);

    /**
     * 根据 ID 删除 DriverAttribute
     *
     * @param id DriverAttributeId
     * @return Boolean
     */
    @PostMapping("/delete/{id}")
    R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改 DriverAttribute
     *
     * @param driverAttribute DriverAttribute
     * @return DriverAttribute
     */
    @PostMapping("/update")
    R<DriverAttribute> update(@Validated(Update.class) @RequestBody DriverAttribute driverAttribute);

    /**
     * 根据 ID 查询 DriverAttribute
     *
     * @param id DriverAttribute Id
     * @return DriverAttribute
     */
    @GetMapping("/id/{id}")
    R<DriverAttribute> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 根据 驱动ID 查询 DriverAttribute
     *
     * @param id DriverAttribute Id
     * @return DriverAttribute
     */
    @GetMapping("/driver_id/{id}")
    R<List<DriverAttribute>> selectByDriverId(@NotNull @PathVariable(value = "id") String id);

    /**
     * 分页查询 DriverAttribute
     *
     * @param driverAttributeDto DriverAttribute Dto
     * @return Page<DriverAttribute>
     */
    @PostMapping("/list")
    R<Page<DriverAttribute>> list(@RequestBody(required = false) DriverAttributeDto driverAttributeDto);

}

package org.industry.api.manager.feign;

import org.industry.api.manager.fallback.BatchClientFallback;
import org.industry.common.bean.R;
import org.industry.common.bean.batch.BatchDriver;
import org.industry.common.constant.ServiceConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 批量导入 FeignClient
 *
 * @author pnoker
 */
@FeignClient(path = ServiceConstant.Manager.BATCH_URL_PREFIX, name = ServiceConstant.Manager.SERVICE_NAME, fallbackFactory = BatchClientFallback.class)
public interface BatchClient {

    /**
     * 批量导入，包含：驱动->模版->驱动配置->位号->设备->位号配置
     *
     * @param multipartFile MultipartFile
     * @return Boolean
     */
    @PostMapping("/import")
    R<Boolean> batchImport(@RequestParam(value = "file") MultipartFile multipartFile);

    /**
     * 批量导入，包含：驱动->模版->驱动配置->位号->设备->位号配置
     *
     * @param batchDrivers List<BatchDriver>
     * @return Boolean
     */
    @PostMapping("/import/batch_driver")
    R<Boolean> batchImport(@RequestBody List<BatchDriver> batchDrivers);

    /**
     * 批量导出，包含：驱动->模版->驱动配置->位号->设备->位号配置
     *
     * @param serviceName 驱动服务名称
     * @return BatchDriver
     */
    @GetMapping("/export/{serviceName}")
    R<BatchDriver> batchExport(@NotNull @PathVariable(value = "serviceName") String serviceName);

}

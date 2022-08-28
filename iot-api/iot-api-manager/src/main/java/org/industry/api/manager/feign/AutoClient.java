package org.industry.api.manager.feign;

import org.industry.api.manager.fallback.AutoClientFallback;
import org.industry.common.bean.R;
import org.industry.common.bean.point.PointDetail;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.valid.Insert;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 自发现 FeignClient
 *
 * @author pnoker
 */
@FeignClient(path = ServiceConstant.Manager.AUTO_URL_PREFIX, name = ServiceConstant.Manager.SERVICE_NAME, fallbackFactory = AutoClientFallback.class)
public interface AutoClient {

    @PostMapping("/create_device_point")
    R<PointDetail> autoCreateDeviceAndPoint(@Validated(Insert.class) @RequestBody PointDetail pointDetail,
                                            @RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

}

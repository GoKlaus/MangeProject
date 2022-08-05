package org.industry.api.manager.feign;

import org.industry.api.manager.fallback.DictionaryClientFallback;
import org.industry.common.bean.Dictionary;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(path = ServiceConstant.Manager.DICTIONARY_URL_PREFIX, name = ServiceConstant.Manager.SERVICE_NAME, fallbackFactory = DictionaryClientFallback.class)
public interface DictionaryClient {


    /**
     * 查询设备 Dictionary
     *
     * @return List<Dictionary>
     */
    @GetMapping("/device")
    R<List<Dictionary>> deviceDictionary(@RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);
}

package org.industry.api.auth.feign;

import org.industry.api.auth.fallback.DictionaryClientFallback;
import org.industry.common.bean.Dictionary;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * 字典 FeignClient
 *
 */
@FeignClient(path = ServiceConstant.Auth.DICTIONARY_URL_PREFIX, name = ServiceConstant.Auth.SERVICE_NAME, fallbackFactory = DictionaryClientFallback.class)
public interface DictionaryClient {

    /**
     * 查询租户 Dictionary
     *
     * @return List<Dictionary>
     */
    @GetMapping("/tenant")
    R<List<Dictionary>> tenantDictionary();

    /**
     * 查询用户 Dictionary
     *
     * @return List<Dictionary>
     */
    @GetMapping("/user")
    R<List<Dictionary>> userDictionary(@RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 查询 Ip 黑名单 Dictionary
     *
     * @return List<Dictionary>
     */
    @GetMapping("/black_ip")
    R<List<Dictionary>> blackIpDictionary(@RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

}

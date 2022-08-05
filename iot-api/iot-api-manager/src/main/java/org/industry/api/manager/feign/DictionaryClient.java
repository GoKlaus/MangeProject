package org.industry.api.manager.feign;

import org.industry.api.manager.fallback.DictionaryClientFallback;
import org.industry.common.bean.Dictionary;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.constraints.NotNull;
import java.util.List;

@FeignClient(path = ServiceConstant.Manager.DICTIONARY_URL_PREFIX, name = ServiceConstant.Manager.SERVICE_NAME, fallbackFactory = DictionaryClientFallback.class)
public interface DictionaryClient {

    /**
     * 查询驱动 Dictionary
     *
     * @return List<Dictionary>
     */
    @GetMapping("/driver")
    R<List<Dictionary>> driverDictionary(@RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 查询驱动属性 Dictionary
     *
     * @return List<Dictionary>
     */
    @GetMapping("/driver_attribute")
    R<List<Dictionary>> driverAttributeDictionary(@RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 查询位号属性 Dictionary
     *
     * @return List<Dictionary>
     */
    @GetMapping("/point_attribute")
    R<List<Dictionary>> pointAttributeDictionary(@RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 查询模板 Dictionary
     *
     * @return List<Dictionary>
     */
    @GetMapping("/profile")
    R<List<Dictionary>> profileDictionary(@RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 查询设备 Dictionary
     *
     * @return List<Dictionary>
     */
    @GetMapping("/device")
    R<List<Dictionary>> deviceDictionary(@RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

    /**
     * 查询位号 Dictionary
     *
     * @param parent profile/device
     * @return List<Dictionary>
     */
    @GetMapping("/point/{parent}")
    R<List<Dictionary>> pointDictionary(@NotNull @PathVariable("parent") String parent, @RequestHeader(value = ServiceConstant.Header.X_AUTH_TENANT_ID, defaultValue = "-1") String tenantId);

}

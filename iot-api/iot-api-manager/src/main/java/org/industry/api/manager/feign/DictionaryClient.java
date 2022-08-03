package org.industry.api.manager.feign;

import org.industry.api.manager.fallback.DictionaryClientFallback;
import org.industry.common.constant.ServiceConstant;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(path = ServiceConstant.Manager.DICTIONARY_URL_PREFIX, name = ServiceConstant.Manager.SERVICE_NAME, fallbackFactory = DictionaryClientFallback.class)
public interface DictionaryClient {
}

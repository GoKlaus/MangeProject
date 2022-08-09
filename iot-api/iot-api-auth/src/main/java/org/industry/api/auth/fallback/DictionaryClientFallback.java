package org.industry.api.auth.fallback;

import lombok.extern.slf4j.Slf4j;
import org.industry.api.auth.feign.DictionaryClient;
import org.industry.common.bean.Dictionary;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DictionaryClientFallback implements FallbackFactory<DictionaryClient> {
    @Override
    public DictionaryClient create(Throwable cause) {
        String message = cause.getMessage() == null ? "No available server for client: " + ServiceConstant.Auth.SERVICE_NAME : cause.getMessage();
        log.error("FallbackL: {}", message);

        return new DictionaryClient() {
            @Override
            public R<List<Dictionary>> tenantDictionary() {
                return null;
            }

            @Override
            public R<List<Dictionary>> userDictionary(String tenantId) {
                return null;
            }

            @Override
            public R<List<Dictionary>> blackIpDictionary(String tenantId) {
                return null;
            }
        };
    }
}

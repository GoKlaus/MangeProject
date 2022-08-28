package org.industry.api.manager.fallback;

import lombok.extern.slf4j.Slf4j;
import org.industry.api.manager.feign.BatchClient;
import org.industry.common.bean.R;
import org.industry.common.bean.batch.BatchDriver;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Component
public class BatchClientFallback implements FallbackFactory<BatchClient> {

    @Override
    public BatchClient create(Throwable throwable) {
        String message = throwable.getMessage() == null ? "No available server for client: DC3-CENTER-MANAGER" : throwable.getMessage();
        log.error("Fallback:{}", message);

        return new BatchClient() {

            @Override
            public R<Boolean> batchImport(MultipartFile multipartFile) {
                return R.fail(message);
            }

            @Override
            public R<Boolean> batchImport(List<BatchDriver> batchDrivers) {
                return R.fail(message);
            }

            @Override
            public R<BatchDriver> batchExport(String serviceName) {
                return R.fail(message);
            }

        };
    }
}
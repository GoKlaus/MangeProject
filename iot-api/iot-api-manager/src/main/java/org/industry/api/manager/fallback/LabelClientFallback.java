package org.industry.api.manager.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.api.manager.feign.LabelClient;
import org.industry.common.bean.R;
import org.industry.common.dto.LabelDto;
import org.industry.common.model.Label;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * LabelClientFallback
 *
 * @author pnoker
 */
@Slf4j
@Component
public class LabelClientFallback implements FallbackFactory<LabelClient> {

    @Override
    public LabelClient create(Throwable throwable) {
        String message = throwable.getMessage() == null ? "No available server for client: DC3-CENTER-MANAGER" : throwable.getMessage();
        log.error("Fallback:{}", message);

        return new LabelClient() {

            @Override
            public R<Label> add(Label label, String tenantId) {
                return R.fail(message);
            }

            @Override
            public R<Boolean> delete(String id) {
                return R.fail(message);
            }

            @Override
            public R<Label> update(Label label, String tenantId) {
                return R.fail(message);
            }

            @Override
            public R<Label> selectById(String id) {
                return R.fail(message);
            }

            @Override
            public R<Page<Label>> list(LabelDto labelDto, String tenantId) {
                return R.fail(message);
            }
        };
    }
}
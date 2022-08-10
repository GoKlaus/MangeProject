package org.industry.api.auth.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.feign.BlackIpClient;
import org.industry.common.dto.BlackIpDto;
import org.industry.common.model.BlackIp;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BlackIpFallback implements FallbackFactory<BlackIpClient> {
    @Override
    public BlackIpClient create(Throwable cause) {
        String message = cause.getMessage() == null ? "No available server for client: " + ServiceConstant.Auth.SERVICE_NAME : cause.getMessage();
        log.error("FallbackL: {}", message);
        return new BlackIpClient() {

            @Override
            public R<Boolean> checkBlackIpValid(String remoteIp) {
                return R.fail(message);
            }

            /**
             * @param blackIp
             * @return
             */
            @Override
            public R<BlackIp> add(BlackIp blackIp) {
                return R.fail(message);
            }

            /**
             * @param id
             * @return
             */
            @Override
            public R<Boolean> delete(String id) {
                return R.fail(message);
            }

            /**
             * @param blackIp
             * @return
             */
            @Override
            public R<BlackIp> update(BlackIp blackIp) {
                return R.fail(message);
            }

            /**
             * @param id
             * @return
             */
            @Override
            public R<BlackIp> selectById(String id) {
                return R.fail(message);
            }

            /**
             * @param ip
             * @return
             */
            @Override
            public R<BlackIp> selectByIp(String ip) {
                return R.fail(message);
            }

            /**
             * @param query
             * @return
             */
            @Override
            public R<Page<BlackIp>> list(BlackIpDto query) {
                return R.fail(message);
            }
        };
    }

}

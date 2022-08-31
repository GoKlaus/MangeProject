package org.industry.api.rtmp.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.api.rtmp.feign.RtmpClient;
import org.industry.common.bean.R;
import org.industry.common.dto.RtmpDto;
import org.industry.common.model.Rtmp;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RtmpFallback implements FallbackFactory<RtmpClient> {
    /**
     * @param throwable
     * @return
     */
    @Override
    public RtmpClient create(Throwable throwable) {
        String message = throwable.getMessage() == null ? "No available server for client: IOT-CENTER-RTMP" : throwable.getMessage();
        log.error("Fallback:{}", message);
        return new RtmpClient() {
            /**
             * 新增 Rtmp
             *
             * @param rtmp     Rtmp
             * @param tenantId
             * @return R<Rtmp>
             */
            @Override
            public R<Rtmp> add(Rtmp rtmp, String tenantId) {
                return R.fail(message);
            }

            /**
             * 根据 ID 删除 Rtmp
             *
             * @param id rtmpId
             * @return R<Boolean>
             */
            @Override
            public R<Boolean> delete(String id) {
                return R.fail(message);
            }

            /**
             * 修改 Rtmp
             *
             * @param rtmp     Rtmp
             * @param tenantId
             * @return R<Rtmp>
             */
            @Override
            public R<Rtmp> update(Rtmp rtmp, String tenantId) {
                return R.fail(message);
            }

            /**
             * 根据 ID 查询 Rtmp
             *
             * @param id Rtmp Id
             * @return R<Rtmp>
             */
            @Override
            public R<Rtmp> selectById(String id) {
                return R.fail(message);
            }

            /**
             * 分页查询 Rtmp
             *
             * @param rtmpDto  Rtmp Dto
             * @param tenantId
             * @return R<Page < Rtmp>>
             */
            @Override
            public R<Page<Rtmp>> list(RtmpDto rtmpDto, String tenantId) {
                return R.fail(message);
            }

            /**
             * 启动 Rtmp 转码任务
             *
             * @param id Rtmp Id
             * @return R<Boolean>
             */
            @Override
            public R<Boolean> start(String id) {
                return R.fail(message);
            }

            /**
             * 停止 Rtmp 转码任务
             *
             * @param id Rtmp Id
             * @return R<Boolean>
             */
            @Override
            public R<Boolean> stop(String id) {
                return R.fail(message);
            }
        };
    }
}

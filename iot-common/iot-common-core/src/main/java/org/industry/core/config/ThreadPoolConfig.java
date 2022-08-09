
package org.industry.core.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.industry.common.property.ThreadProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "server")
public class ThreadPoolConfig {

    @Setter
    private ThreadProperty thread;

    private final AtomicInteger threadPoolAtomic = new AtomicInteger(1);
    private final AtomicInteger scheduledThreadPoolAtomic = new AtomicInteger(1);

    /**
     * LinkedBlockingQueue ThreadPoolExecutor
     */
    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(
                thread.getCorePoolSize(),
                thread.getMaximumPoolSize(),
                thread.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(thread.getMaximumPoolSize() * 2),
                (r) -> new Thread(r, "[T]" + thread.getPrefix() + threadPoolAtomic.getAndIncrement()),
                (r, e) -> new BlockingRejectedExecutionHandler());
    }

    @Bean
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
        return new ScheduledThreadPoolExecutor(
                thread.getCorePoolSize(),
                (r) -> new Thread(r, "[S]" + thread.getPrefix() + scheduledThreadPoolAtomic.getAndIncrement()),
                (r, e) -> new BlockingRejectedExecutionHandler());
    }

    private static class BlockingRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
            try {
                log.info("BlockingRejectedExecutionHandler: {}", executor.toString());

                if (!executor.isShutdown()) {
                    runnable.run();
                }
            } catch (Exception e) {
                log.error("BlockingRejectedExecutionHandler: {}", e.getMessage(), e);
            }
        }
    }

}

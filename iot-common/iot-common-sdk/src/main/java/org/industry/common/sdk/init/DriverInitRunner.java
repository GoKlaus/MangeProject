package org.industry.common.sdk.init;

import org.industry.common.sdk.bean.driver.DriverProperty;
import org.industry.common.sdk.service.DriverCustomService;
import org.industry.common.sdk.service.DriverMetadataService;
import org.industry.common.sdk.service.DriverScheduleService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@ComponentScan(basePackages = {"org.industry.common.sdk"})
@EnableConfigurationProperties(DriverProperty.class)
public class DriverInitRunner implements ApplicationRunner {

    @Resource
    private DriverMetadataService driverMetadataService;
    @Resource
    private DriverCustomService driverCustomService;
    @Resource
    private DriverScheduleService driverScheduleService;
    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        driverMetadataService.initial();
        driverCustomService.initial();
        driverScheduleService.initial();
    }
}

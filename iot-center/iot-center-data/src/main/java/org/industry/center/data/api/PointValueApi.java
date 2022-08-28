package org.industry.center.data.api;

import lombok.extern.slf4j.Slf4j;
import org.industry.center.data.service.PointValueService;
import org.industry.common.constant.ServiceConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(ServiceConstant.Data.VALUE_URL_PREFIX)
public class PointValueApi {

    @Resource
    private PointValueService pointValueService;
}

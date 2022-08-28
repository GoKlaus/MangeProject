package org.industry.center.data.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.industry.center.data.service.PointValueService;
import org.industry.core.utils.RedisUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@Slf4j
public class PointValueServiceImpl implements PointValueService {

    @Resource
    private RedisUtil redisUtil;
//    @Resource
//    private PointClient pointClient;
//    @Resource
//    private DeviceClient deviceClient;
    @Resource
    private MongoTemplate mongoTemplate;
//    @Resource
//    private PointValueHandleService pointValueHandleService;
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
}

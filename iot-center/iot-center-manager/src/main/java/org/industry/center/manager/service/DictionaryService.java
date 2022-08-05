package org.industry.center.manager.service;

import org.industry.common.bean.Dictionary;

import java.util.List;

public interface DictionaryService {

    /**
     * 获取租户驱动字典
     *
     * @return Dictionary Array
     */
    List<Dictionary> driverDictionary(String tenantId);
    List<Dictionary> deviceDictionary(String tenantId);
}

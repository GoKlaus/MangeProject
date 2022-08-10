package org.industry.center.auth.service;


import org.industry.common.bean.Dictionary;

import java.util.List;

public interface DictionaryService {
    List<Dictionary> tenantDictionary();

    List<Dictionary> userDictionary();

    List<Dictionary> blackIpDictionary();

}

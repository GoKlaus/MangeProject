package org.industry.center.auth.api;

import lombok.extern.slf4j.Slf4j;
import org.industry.api.auth.feign.DictionaryClient;
import org.industry.center.auth.service.DictionaryService;
import org.industry.common.bean.Dictionary;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(ServiceConstant.Auth.DICTIONARY_URL_PREFIX)
public class DictionaryApi implements DictionaryClient {

    @Resource
    private DictionaryService dictionaryService;

    @Override
    public R<List<Dictionary>> tenantDictionary() {
        return null;
    }

    @Override
    public R<List<Dictionary>> userDictionary(String tenantId) {
        return null;
    }

    @Override
    public R<List<Dictionary>> blackIpDictionary(String tenantId) {
        return null;
    }
}

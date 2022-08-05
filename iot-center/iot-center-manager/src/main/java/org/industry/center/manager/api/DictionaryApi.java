package org.industry.center.manager.api;

import lombok.extern.slf4j.Slf4j;
import org.industry.api.manager.feign.DictionaryClient;
import org.industry.center.manager.service.DictionaryService;
import org.industry.common.bean.Dictionary;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(ServiceConstant.Manager.DICTIONARY_URL_PREFIX)
public class DictionaryApi implements DictionaryClient {

    @Resource
    private DictionaryService dictionaryService;

    @Override
    public R<List<Dictionary>> deviceDictionary(String tenantId) {
        try {
            List<Dictionary> dictionaryList = dictionaryService.deviceDictionary(tenantId);
            if (null != dictionaryList) {
                return R.ok(dictionaryList);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<List<Dictionary>> driverDictionary(String tenantId) {
        try {
            List<Dictionary> dictionaryList = dictionaryService.driverDictionary(tenantId);
            if (null != dictionaryList) {
                return R.ok(dictionaryList);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<List<Dictionary>> driverAttributeDictionary(String tenantId) {
        return null;
    }

    @Override
    public R<List<Dictionary>> pointAttributeDictionary(String tenantId) {
        return null;
    }

    @Override
    public R<List<Dictionary>> profileDictionary(String tenantId) {
        try {
            List<Dictionary> dictionaryList = dictionaryService.profileDictionary(tenantId);
            if (null != dictionaryList) {
                return R.ok(dictionaryList);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<List<Dictionary>> pointDictionary(String parent, String tenantId) {
        return null;
    }
}

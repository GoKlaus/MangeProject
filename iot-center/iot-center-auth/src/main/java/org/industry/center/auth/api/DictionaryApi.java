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
        try {
            List<Dictionary> list = dictionaryService.tenantDictionary();
            if (null != list) {
                return R.ok(list);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<List<Dictionary>> userDictionary(String tenantId) {
        try {
            List<Dictionary> list = dictionaryService.userDictionary();
            if (null != list) {
                return R.ok(list);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }

    @Override
    public R<List<Dictionary>> blackIpDictionary(String tenantId) {
        try {
            List<Dictionary> list = dictionaryService.blackIpDictionary();
            if (null != list) {
                return R.ok(list);
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail();
    }
}

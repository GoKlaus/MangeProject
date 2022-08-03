package org.industry.center.manager.api;

import lombok.extern.slf4j.Slf4j;
import org.industry.api.manager.feign.DictionaryClient;
import org.industry.common.constant.ServiceConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ServiceConstant.Manager.DICTIONARY_URL_PREFIX)
public class DictionaryApi implements DictionaryClient {


}

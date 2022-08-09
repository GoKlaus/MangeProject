package org.industry.center.auth.service;

import org.industry.common.base.Service;
import org.industry.common.dto.BlackIpDto;
import org.industry.common.model.BlackIp;

public interface BlackIpService extends Service<BlackIp, BlackIpDto> {
    boolean checkBlackIpValid(String ip);

    BlackIp selectByIp(String ip);
}

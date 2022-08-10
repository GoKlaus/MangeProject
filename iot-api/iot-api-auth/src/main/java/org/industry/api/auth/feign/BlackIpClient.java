package org.industry.api.auth.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.api.auth.fallback.BlackIpFallback;
import org.industry.common.dto.BlackIpDto;
import org.industry.common.model.BlackIp;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

@FeignClient(path = ServiceConstant.Auth.BLACK_IP_URL_PREFIX, name = ServiceConstant.Auth.SERVICE_NAME, fallbackFactory = BlackIpFallback.class)
public interface BlackIpClient {

    @PostMapping("/add")
    R<BlackIp> add(@Validated(Insert.class) @RequestBody BlackIp blackIp);

    @GetMapping("/delete/{id}")
    R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    @PostMapping("/update")
    R<BlackIp> update(@Validated(Update.class) @RequestBody BlackIp blackIp);

    @GetMapping("/id/{id}")
    R<BlackIp> selectById(@NotNull @PathVariable String id);

    @GetMapping("/ip/{ip}")
    R<BlackIp> selectByIp(@NotNull @PathVariable String ip);

    @PostMapping("/list")
    R<Page<BlackIp>> list(@RequestBody(required = false) BlackIpDto query);

    /**
     * 检查黑名单
     *
     * @param remoteIp
     * @return
     */
    @GetMapping("/check/{ip}")
    R<Boolean> checkBlackIpValid(@NotNull @PathVariable("ip") String remoteIp);

}

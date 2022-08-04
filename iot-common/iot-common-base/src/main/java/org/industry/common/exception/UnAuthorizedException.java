package org.industry.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义 未授权 异常
 *
 * @author pnoker
 */
public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException(CharSequence template, Object... params) {
        super(StrUtil.format(template, params));
    }
}
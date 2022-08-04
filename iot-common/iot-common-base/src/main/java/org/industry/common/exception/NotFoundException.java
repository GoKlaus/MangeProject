package org.industry.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义 Null 异常
 *
 * @author pnoker
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(CharSequence template, Object... params) {
        super(StrUtil.format(template, params));
    }
}

package org.industry.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义 Empty 异常
 *
 */
public class EmptyException extends RuntimeException {
    public EmptyException(CharSequence template, Object... params) {
        super(StrUtil.format(template, params));
    }
}

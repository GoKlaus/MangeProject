package org.industry.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义 新增数据 异常
 *
 */
public class AddException extends RuntimeException {
    public AddException(CharSequence template, Object... params) {
        super(StrUtil.format(template, params));
    }
}

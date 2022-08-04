package org.industry.common.exception;

import cn.hutool.core.util.StrUtil;

/**
 * 自定义 重复 异常
 *
 * @author pnoker
 */
public class DuplicateException extends RuntimeException {
    public DuplicateException(CharSequence template, Object... params) {
        super(StrUtil.format(template, params));
    }
}

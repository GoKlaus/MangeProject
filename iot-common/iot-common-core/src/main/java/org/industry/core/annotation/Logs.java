package org.industry.core.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logs {

    String value();

}

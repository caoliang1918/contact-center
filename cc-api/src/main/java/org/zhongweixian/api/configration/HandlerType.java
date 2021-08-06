package org.zhongweixian.api.configration;

import java.lang.annotation.*;

/**
 * Create by caoliang on 2020/8/23
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface HandlerType {
    String value();
}
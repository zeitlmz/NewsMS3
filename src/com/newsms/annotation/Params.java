package com.newsms.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author lmz
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface Params {

    Class<?> cls() default Object.class;

    String value() default "";

    String classType() default "";

    boolean required() default true;
}

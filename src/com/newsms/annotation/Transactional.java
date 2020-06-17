package com.newsms.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author lmz
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface Transactional {
    String value() default "";
}

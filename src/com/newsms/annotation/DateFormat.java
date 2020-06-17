package com.newsms.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author lmz
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface DateFormat {
    String format() default "yyyy-MM-dd";
}

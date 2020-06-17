package com.newsms.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author lmz
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface NotNull {
    boolean value() default false;
}

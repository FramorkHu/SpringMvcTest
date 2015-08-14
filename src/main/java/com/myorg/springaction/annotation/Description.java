package com.myorg.springaction.annotation;

import java.lang.annotation.*;

/**
 * Created by huyan on 15/7/30.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Description {

    String value();
}

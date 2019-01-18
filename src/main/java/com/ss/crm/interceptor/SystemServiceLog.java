package com.ss.crm.interceptor;

import java.lang.annotation.*;

/*拦截service*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface SystemServiceLog {
    /*定义成员*/
    String decription() default "" ;
}

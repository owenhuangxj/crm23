package com.ss.crm.interceptor;


import java.lang.annotation.*;

/*添加拦截日志
拦截controller
@Retention(RetentionPolicy.RUNTIME)
*元注解，定义注解被保留策略，一般有三种策略
*   1、RetentionPolicy.SOURCE 注解只保留在源文件中，在编译成class文件的时候被遗弃
    2、RetentionPolicy.CLASS 注解被保留在class中，但是在jvm加载的时候北欧抛弃，这个是默认的声明周期
    3、RetentionPolicy.RUNTIME 注解在jvm加载的时候仍被保留

    @Target({ElementType.PARAMETER,ElementType.METHOD})
    定义了注解声明在哪些元素之前
* */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
    /*描述操作*/
    String descrption() default "" ;
    /*操作的类型，1、用户操作 2、添加 3、删除 4、修改 5、查询  */
    String actionType() default "" ;

}

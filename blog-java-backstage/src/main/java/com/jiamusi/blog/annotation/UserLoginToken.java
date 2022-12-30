package com.jiamusi.blog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：Mr.ZJW
 * @date ：Created 2022/2/28 10:26
 * @description：用于登录后才能操作的token
 */
/*RetentionPolicy.RUNTIME:这种类型的Annotations将被JVM保留,
所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用。*/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}

package com.yxs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author YXS
 * @PackageName: com.yxs.annotation
 * @ClassName: SystemLog
 * @Desription:
 * @date 2023/3/21 17:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SystemLog {

    String businessName();

}

package com.bbsuper.nev.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不需要进行权限拦截
 * @author liwei
 * @date: 2018年10月24日 下午4:10:23
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreAuthentication {
	
	/**
	 * 默认不需要登录
	 * @return
	 */
	boolean needLogin() default false;

}

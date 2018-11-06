package com.bbsuper.nev.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 日志处理
 * @author liwei
 * @date: 2018年10月23日 上午9:46:13
 *
 */

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Slf4j {
	/**
	 * 日志内容
	 * @return
	 */
	String value() default "";
	
	/**
	 * 是否记录响应时间
	 * @return
	 */
	boolean logTime() default true;
	
	/**
	 * 是否记录异常日志
	 * @return
	 */
	boolean logError() default true;
	
    
}

package com.bbsuper.nev.config.thread;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;


/**
 * 修改springBoot @Async 默认使用的线程池
 * @author liwei
 * @date: 2018年11月7日 下午2:39:32
 *
 */
@Configuration
public class MyAsyncConfigurer implements AsyncConfigurer{
	
	private static Logger logger = LoggerFactory.getLogger(MyAsyncConfigurer.class);
	
	@Autowired
	private AsyncThreadPool asyncThreadPool;

	@Override
	public Executor getAsyncExecutor() {
		return asyncThreadPool;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler(){
			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				logger.error("asyncThreadPool task error,{}.{}()",method.getDeclaringClass().getSimpleName(),method.getName(),ex);
			}
			
		};
	}
	
	
	
}

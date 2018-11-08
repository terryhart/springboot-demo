package com.bbsuper.nev.config.thread;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.bbsuper.nev.beans.vo.user.UserInfo;
import com.bbsuper.nev.utils.ControllerUtil;

/**
 * 异步线程池
 * 可配合注解使用@Async("asyncThreadPool")
 * 可继承调用线程的MDC信息，以及User信息
 * @author liwei
 * @date: 2018年11月7日 下午1:42:53
 *
 */
@Component("asyncThreadPool")
public class AsyncThreadPool extends ThreadPoolTaskExecutor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2305730842681731330L;
	
	@Autowired
	private ThreadPoolConfig threadPoolConfig;

	@Override
	public void initialize() {
		setCorePoolSize(threadPoolConfig.getCorePoolSize());
		setMaxPoolSize(threadPoolConfig.getMaxPoolSize());
		setQueueCapacity(threadPoolConfig.getQueueCapacity());
		setKeepAliveSeconds(threadPoolConfig.getKeepAliveSeconds());
		setThreadNamePrefix("AsyncThreadPool-");
		setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		super.initialize();
	}

	@Override
	public void execute(Runnable task) {
		super.execute(proxyRunnable(task));
	}
		


	@Override
	public Future<?> submit(Runnable task) {
		return super.submit(proxyRunnable(task));
		
	}
	
	/**
	 * spring @Async 默认调用该方法
	 */
	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return super.submit(proxyCallable(task));
	}
	
	
	
	
	private Runnable proxyRunnable(Runnable task){
		Map<String, String> contextMap = MDC.getCopyOfContextMap();
		UserInfo currentUser = ControllerUtil.getCurrentUser();
		return ()->{
			try{
				ControllerUtil.putUser(currentUser);
				if(contextMap!=null){
					MDC.setContextMap(contextMap);
				}
				task.run();
			}finally{
				MDC.clear();
				ControllerUtil.removeUser();
			}
		};
	}
	
	private <T> Callable<T> proxyCallable(Callable<T> task){
		Map<String, String> contextMap = MDC.getCopyOfContextMap();
		UserInfo currentUser = ControllerUtil.getCurrentUser();
		return ()->{
			try{
				ControllerUtil.putUser(currentUser);
				if(contextMap!=null){
					MDC.setContextMap(contextMap);
				}
				return task.call();
			}finally{
				MDC.clear();
				ControllerUtil.removeUser();
			}
		};
	}


}

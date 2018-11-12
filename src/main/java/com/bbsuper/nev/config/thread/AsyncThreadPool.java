package com.bbsuper.nev.config.thread;
import java.lang.reflect.Field;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 异步线程池
 * 可配合注解使用@Async("asyncThreadPool")
 * 可继承调用线程ThreadLocal,并支持动态变化
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
		Thread originalThread = Thread.currentThread();
		super.execute(()->{
			copyThreadLocals(originalThread);
			task.run();
		});
	}
		


	@Override
	public Future<?> submit(Runnable task) {
		Thread originalThread = Thread.currentThread();
		return super.submit(()->{
			copyThreadLocals(originalThread);
			task.run();
		});
		
	}
	
	/**
	 * spring @Async 默认调用该方法
	 */
	@Override
	public <T> Future<T> submit(Callable<T> task) {
		Thread originalThread = Thread.currentThread();
		return super.submit(()->{
			copyThreadLocals(originalThread);
			return task.call();
		});
	}
	
	
	/**
	 * 复制原始线程的ThreadLocal内容到当前线程
	 * @param originalThread
	 */
	private void copyThreadLocals(Thread originalThread) {
		try{
			Field threadLocals = Thread.class.getDeclaredField("threadLocals");
			threadLocals.setAccessible(true);
			threadLocals.set(Thread.currentThread(), threadLocals.get(originalThread));
			
			Field inheritableThreadLocals = Thread.class.getDeclaredField("inheritableThreadLocals");
			inheritableThreadLocals.setAccessible(true);
			inheritableThreadLocals.set(Thread.currentThread(), inheritableThreadLocals.get(originalThread));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}

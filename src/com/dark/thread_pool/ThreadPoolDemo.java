package com.dark.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月26日 上午10:20:17
 */
public class ThreadPoolDemo {
	/**
	 * FixedThreadPool Demo
	 * 	固定数量的线程池
	 */
	public static void fixedThreadPoolDemo() {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			final int taskCount = i;
			fixedThreadPool.execute(() -> {
				for (int j = 0; j < 4; j++)
					System.out.println(Thread.currentThread().getName()+ " of task " + taskCount + " has been looped of "+ j);
			});
		}
		System.err.println("all of tasks have committed! \n");
		fixedThreadPool.shutdown();
	}
	
	/**
	 * CatchThreadPool Demo
	 * 	缓存的线程池，每一个新任务都会新new一个线程。当线程结束就从池子中移除线程
	 */
	public static void catchThreadPoolDemo() {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int taskCount = i;
			cachedThreadPool.execute(()->{
				for (int j = 0; j < 4; j++) {
					System.out.println(Thread.currentThread().getName()+ " of task " + taskCount + " has been looped of "+ j);
				}
			});
		}
		System.err.println("all of tasks have committed!\n");
		cachedThreadPool.shutdown();
	}
	/**
	 * ScheduledThreadPool Demo
	 * 计划的线程池，线程在延迟后执行
	 */
	public static void scheduledThreadPoolDemo(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
		scheduledThreadPool.schedule(()->{
			System.out.println(Thread.currentThread().getName());
		}, 10, TimeUnit.SECONDS);
		scheduledThreadPool.shutdown();
		
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.schedule(()->{
			System.out.println(Thread.currentThread().getName());
		}, 3,TimeUnit.SECONDS);
		scheduledExecutorService.scheduleWithFixedDelay(()->{
			System.out.println(Thread.currentThread().getName());
		}, 3, 2, TimeUnit.SECONDS);
		scheduledExecutorService.shutdown();
	}
	/**
	 * SingleThreadExecutor Demo
	 * 单一线程池，当线程死掉后就重新启动该线程
	 */
	public static void singleThreadExecutorDemo(){
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 4; i++) {
			singleThreadExecutor.execute(()->{
				System.out.println(Thread.currentThread().getName());
			});
		}
		singleThreadExecutor.shutdown();
	}
	/**
	 * main method
	 */
	public static void main(String[] args) {
//		ThreadPoolDemo.fixedThreadPoolDemo();
//		ThreadPoolDemo.catchThreadPoolDemo();
		ThreadPoolDemo.scheduledThreadPoolDemo();
//		ThreadPoolDemo.singleThreadExecutorDemo();
	}
}

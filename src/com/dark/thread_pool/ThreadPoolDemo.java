package com.dark.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��26�� ����10:20:17
 */
public class ThreadPoolDemo {
	/**
	 * FixedThreadPool Demo
	 * 	�̶��������̳߳�
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
	 * 	������̳߳أ�ÿһ�������񶼻���newһ���̡߳����߳̽����ʹӳ������Ƴ��߳�
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
	 * �ƻ����̳߳أ��߳����ӳٺ�ִ��
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
	 * ��һ�̳߳أ����߳�������������������߳�
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

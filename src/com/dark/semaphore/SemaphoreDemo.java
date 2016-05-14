package com.dark.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月30日 上午12:30:22
 */
public class SemaphoreDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		/**
		 * permits the initial number of permits available. This value may be negative, in which case releases must occur before any acquires will be granted.
		 * fair true if this semaphore will guarantee first-in first-out granting of permits under contention, else false
		 */
		final Semaphore semaphore = new Semaphore(3,true);
		for (int i = 0; i < 10; i++) {
			executorService.execute(()->{
				try {
					semaphore.acquire();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName()+"进入，当前已有 "+(3-semaphore.availablePermits())+"个并发。");
				try {
					Thread.sleep((long) (Math.random()*3000));
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"即将离开。");
				semaphore.release();
				//下面代码有时候执行的不准确,由于输出语句和上面的release()方法不是原子操作.
				System.out.println(Thread.currentThread().getName()+"已离开，当前已有 "+(3-semaphore.availablePermits())+"个并发。");
			});
		}
		executorService.shutdown();
	}

}

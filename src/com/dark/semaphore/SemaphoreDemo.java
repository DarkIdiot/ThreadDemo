package com.dark.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��30�� ����12:30:22
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
				
				System.out.println(Thread.currentThread().getName()+"���룬��ǰ���� "+(3-semaphore.availablePermits())+"��������");
				try {
					Thread.sleep((long) (Math.random()*3000));
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"�����뿪��");
				semaphore.release();
				//���������ʱ��ִ�еĲ�׼ȷ,����������������release()��������ԭ�Ӳ���.
				System.out.println(Thread.currentThread().getName()+"���뿪����ǰ���� "+(3-semaphore.availablePermits())+"��������");
			});
		}
		executorService.shutdown();
	}

}

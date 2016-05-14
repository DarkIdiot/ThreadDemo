package com.dark.count_down_latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月30日 上午1:16:09
 */
public class CountDownLatchDemo {
	/**
	 * 实现一个线程通知多个线程(短跑,起点统一)
	 */
	public static void orderDemo() {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch order = new CountDownLatch(1);
		for (int i = 0; i < 3; i++) {
			service.execute(() -> {
				try {
					System.out.println(Thread.currentThread().getName()
							+ " has ready to accept commands.");
					order.await();
					System.out.println(Thread.currentThread().getName()
							+ " has accepted commands.");
					Thread.sleep((long) (Math.random() * 3000));
					System.out.println(Thread.currentThread().getName()
							+ " has accomplished.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		try {
			Thread.sleep((long) (Math.random() * 5000));
			System.out.println(Thread.currentThread().getName()
					+ " Command will be published right away.");
			order.countDown();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		service.shutdown();
	}

	/**
	 * 实现多个线程通知一个线程(长跑,终点计时)
	 */
	public static void answerDemo() {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch answer = new CountDownLatch(3);
		for (int i = 0; i < 3; i++) {
			service.execute(() -> {
				try {
					System.out.println(Thread.currentThread().getName()
							+ " has ready to accept commands.");
					System.out.println(Thread.currentThread().getName()
							+ " has accepted commands.");
					Thread.sleep((long) (Math.random() * 3000));
					answer.countDown();
					System.out.println(Thread.currentThread().getName()
							+ " has accomplished.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		try {
			System.out.println(Thread.currentThread().getName()
					+ " is waitting for an acknowledge for the Command.");
			answer.await();
			System.out.println(Thread.currentThread().getName()
					+ " has accepted all the result for the Command.");
		} catch (Exception e2) {

		}
		service.shutdown();
	}

	/**
	 * main method
	 */
	public static void main(String[] args) {
		// CountDownLatchDemo.orderDemo();
		CountDownLatchDemo.answerDemo();
	}

}

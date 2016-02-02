package com.dark.count_down_latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.print.attribute.standard.SheetCollate;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��30�� ����1:16:09
 */
public class CountDownLatchDemo {
	/**
	 * ʵ��һ���߳�֪ͨ����߳�(����,���ͳһ)
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
	 * ʵ�ֶ���߳�֪ͨһ���߳�(����,�յ��ʱ)
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

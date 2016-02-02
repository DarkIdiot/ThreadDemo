package com.dark.cyclic_barrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��30�� ����12:57:32
 */
public class CyclicBarrierDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final CyclicBarrier barrier = new CyclicBarrier(3);
		for (int i = 0; i < 3; i++) {
			executorService.execute(() -> {
				try {
					Thread.sleep((long) (3000 * Math.random()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()
						+ "�������Ｏ�ϵ�1,��ǰ����" + (barrier.getNumberWaiting() + 1)
						+ "���Ѿ����"+(barrier.getNumberWaiting() == 2 ? "��������...":"���ڵȴ�..."));
				try {
					barrier.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep((long) (3000 * Math.random()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()
						+ "�������Ｏ�ϵ�2,��ǰ����" + (barrier.getNumberWaiting() + 1)
						+ "���Ѿ����"+(barrier.getNumberWaiting() == 2 ? "��������...":"���ڵȴ�..."));
				try {
					barrier.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep((long) (3000 * Math.random()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()
						+ "�������Ｏ�ϵ�3,��ǰ����" + (barrier.getNumberWaiting() + 1)
						+ "���Ѿ����"+(barrier.getNumberWaiting() == 2 ? "��������...":"���ڵȴ�..."));
				try {
					barrier.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
	}
}

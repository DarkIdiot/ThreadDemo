package com.dark.cyclic_barrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月30日 上午12:57:32
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
						+ "即将到达集合点1,当前已有" + (barrier.getNumberWaiting() + 1)
						+ "个已经到达。"+(barrier.getNumberWaiting() == 2 ? "即将出发...":"正在等待..."));
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
						+ "即将到达集合点2,当前已有" + (barrier.getNumberWaiting() + 1)
						+ "个已经到达。"+(barrier.getNumberWaiting() == 2 ? "即将出发...":"正在等待..."));
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
						+ "即将到达集合点3,当前已有" + (barrier.getNumberWaiting() + 1)
						+ "个已经到达。"+(barrier.getNumberWaiting() == 2 ? "即将出发...":"正在等待..."));
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

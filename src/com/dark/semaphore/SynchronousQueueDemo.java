package com.dark.semaphore;

import java.util.concurrent.SynchronousQueue;
/**
 * @author idiot
 * @version 1.0
 * @date 2016年3月5日 上午19:44:22
 */
public class SynchronousQueueDemo {
	/**
	 * SynchronousQueue 同步队列,which each insert operation must wait for a corresponding remove operation by another thread.
	 * note:直接insert会阻塞线程.
	 */
	public static void main(String[] args) {
		SynchronousQueue<String> queue = new SynchronousQueue<String>();
		for (int i = 0; i < 3; i++) {
			new Thread(() -> {
				while (true) {
					try {
						System.out.println(Thread.currentThread() + " : " + System.currentTimeMillis()/1000 + " , data = " + queue.take());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				queue.put(i + "");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

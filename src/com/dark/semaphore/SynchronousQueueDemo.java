package com.dark.semaphore;

import java.util.concurrent.SynchronousQueue;
/**
 * @author idiot
 * @version 1.0
 * @date 2016��3��5�� ����19:44:22
 */
public class SynchronousQueueDemo {
	/**
	 * SynchronousQueue ͬ������,which each insert operation must wait for a corresponding remove operation by another thread.
	 * note:ֱ��insert�������߳�.
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

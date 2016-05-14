package com.dark.array_blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用两个具有1个空间的队列来实现同步通知的功能。
 * 
 * @author idiot
 * @version 1.0
 * @date 2016年2月2日 下午10:21:42
 */
public class BlockQueueCommunication {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		Communication communication = new Communication();
		service.execute(() -> {
			for (int i = 0; i < 100; i++)
				communication.threadOne();
		});
		service.execute(() -> {
			for (int i = 0; i < 100; i++)
				communication.threadTwo();
		});
		service.shutdown();
	}
}

class Communication {
	BlockingQueue<Object> blockingQueue1 = new ArrayBlockingQueue<Object>(1);
	BlockingQueue<Object> blockingQueue2 = new ArrayBlockingQueue<Object>(1);
	{
		try {
			blockingQueue2.put("lock");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void threadOne() {
		try {
			blockingQueue1.put("d");
			System.out.println(Thread.currentThread().getName()
					+ ": Thread One");
			blockingQueue2.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void threadTwo() {
		try {
			blockingQueue2.put("d");
			System.out
					.println(Thread.currentThread().getName() + ":Thread Two");
			blockingQueue1.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
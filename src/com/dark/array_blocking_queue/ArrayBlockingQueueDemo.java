package com.dark.array_blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 			Throws exception		Special value		Blocks				Times out		
 *	Insert	add(e)					offer(e)			put(e)				offer(e, time, unit)
 *	Remove	remove()				poll()				take()				poll(time, unit)
 *	Examine	element()				peek()				not applicable		not applicable
 * @author idiot
 * @version 1.0
 * @date 2016年2月1日 下午10:57:59
 */
public class ArrayBlockingQueueDemo {
	public static void main(String[] args) {
		ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(3);
		
		for (int i = 0; i < 20; i++) {
			new Thread(()->{
				try {
					double random = Math.random();
					Thread.sleep((long) (random * 5000));
					System.out.println(Thread.currentThread().getName()+" is ready to put data. data:["+random+"]");
					queue.put(random);
					System.out.println(Thread.currentThread().getName()+" has put data into queue. At the present time, queue's size is "+queue.size());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
		for (int i = 0; i < 20; i++) {
			new Thread(()->{
				try {
					double random = Math.random();
					Thread.sleep((long) (random * 7000));
					System.out.println(Thread.currentThread().getName()+" is ready to get data.");
					Object object = queue.take();
					System.out.println(Thread.currentThread().getName()+" has get data from queue. data:["+object+"]; At the present time, queue's size is "+queue.size());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}

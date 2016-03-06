package com.dark.concurrent.lock_condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��29�� ����3:43:29
 */
public class BoundedBufferDemo {
	public static void main(String[] args) {
		BoundedBuffer bb = new BoundedBuffer();
		new Thread(()->{
			for (int i = 0; i < 10000; i++) {
				try {
					bb.put(i);
					System.out.println(Thread.currentThread().getName()+" has put data : "+i+" in Buffer");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(()->{
			for (int i = 0; i < 10000; i++) {
				try {
					bb.take();
					System.out.println(Thread.currentThread().getName()+" has taken data : "+i+" from Buffer");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
/**
 *	��������ѭ������ 
 */
class BoundedBuffer {
	final Lock lock = new ReentrantLock();
	/**
	 * Ϊʲô������2��Condition.���ʹ��һ��Condition�����ʲô���⣿(����ͬʱ�ж������д�̵߳�ʱ����������)
	 */
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	final Object[] items = new Object[100];
	int putptr, takeptr, count;

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length)
				notFull.await();
			items[putptr] = x;
			if (++putptr == items.length)
				putptr = 0;
			++count;
			System.out.println("putptr:"+putptr+" ,takeptr:"+takeptr+" ,count:"+count);
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0)
				notEmpty.await();
			Object x = items[takeptr];
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			System.out.println("putptr:"+putptr+" ,takeptr:"+takeptr+" ,count:"+count);
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}
}


package com.dark.concurrent.lock_condition;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月26日 下午5:26:29
 */
public class ReadWriteLockDemo {
	public static void main(String[] args) {
		SharedData shareData = new SharedData();
		for (int i = 0; i < 4; i++) {
			new Thread(()->{
				shareData.put(Math.random()*10000);
			}).start();
		}
		for (int i = 0; i < 4; i++) {
			new Thread(()->{
				shareData.get();
			}).start();
		}
	}
}
/**
 *	读写锁:多个读锁不互斥,读锁与写锁互斥,写锁与写锁互斥,这是由jvm控制的。
 */
class SharedData{
	private Object data = null;
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public void get(){
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep((long) (Math.random()*1000));
			System.out.println(Thread.currentThread().getName() + " have read data : "+data);
		}catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			rwl.readLock().unlock();
		}
	}
	public void put(Object data){
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");
			Thread.sleep((long) (Math.random()*1000));
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " have write data : "+data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.writeLock().unlock();
		}
	}
}
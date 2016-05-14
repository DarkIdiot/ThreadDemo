package com.dark.concurrent.lock_condition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月26日 下午4:43:16
 */
public class LockDemo {
	/**
	 *	main method 
	 */
	public static void main(String[] args) {
		LockDemo.reentrantLockDemo();
	}
	
	static class WatchDog{
		private Lock lock = new ReentrantLock();
		
		public void welcome(String personName){
			lock.lock();
			try{
			String str = "Hi "+personName+" , welcome to your HOME !\n";
			for (int i = 0; i < str.length(); i++) {
				System.out.print(str.charAt(i));
			}
			}finally{    
	//为什么需要把解锁的步骤放在try-catch-finally代码块的finally中？是为了预防被同步的代码块发生异常，而导致的线程死亡而并未解锁的情况。
	//线程意外死亡而导致的未解锁会使得其他与其同步的线程发生阻塞。			
				lock.unlock();
			}
		}
	}
	public static void reentrantLockDemo() {
		WatchDog wd = new WatchDog();
		new Thread(()->{
			while (true) {
				wd.welcome(Thread.currentThread().getName());
			}
		}).start();
		new Thread(()->{
			while (true) {
				wd.welcome(Thread.currentThread().getName());
			}
		}).start();
		new Thread(()->{
			while (true) {
				wd.welcome(Thread.currentThread().getName());
			}
		}).start();
	}
}
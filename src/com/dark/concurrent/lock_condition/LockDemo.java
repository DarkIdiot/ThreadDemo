package com.dark.concurrent.lock_condition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��26�� ����4:43:16
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
	//Ϊʲô��Ҫ�ѽ����Ĳ������try-catch-finally������finally�У���Ϊ��Ԥ����ͬ���Ĵ���鷢���쳣�������µ��߳���������δ�����������
	//�߳��������������µ�δ������ʹ����������ͬ�����̷߳���������			
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







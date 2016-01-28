package com.dark.thread_scope_share_data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程绑定数据对象，对于不同的线程，取得不同的值。（使用Map<Thread,Object>实现）
 * @author idiot
 * @version 1.0
 * @date 2016年1月18日 下午9:23:29
 */
public class ThreadScopeShareData {
	/**
	 * All Thread share one static data Object, and the data Object's value will be override by the last Thread.
	 */
	static class A {
		public Object getData(){
			return data;
		}
	}
	static class B {
		public Object getData(){
			return data;
		}
	}
	private static Object data;
	public static void shareDataDemo() {
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					/**
					 * 加入同步锁是为了更加方便的看出效果。在不加同步锁的情况下，很难得到二个不同的data数据,后一个线程总是会覆盖前面的线程装入的数据。
					 */
					synchronized (ThreadScopeShareData.class) {
						data = new Random().nextInt();
						System.out.println("Thread:"+Thread.currentThread().getName()+" has loaded data -> "+data);
					}
					System.out.println("Thread:"+Thread.currentThread().getName()+" A.getData() -> "+new A().getData());
					System.out.println("Thread:"+Thread.currentThread().getName()+" B.getData() -> "+new B().getData());
				}
			}).start();
		}
	}
	
	/**
	 * 
	 */
	static class C {
		public Object getData(Thread thread){
			return dataMap.get(thread);
		}
	}
	static class D {
		public Object getData(Thread thread){
			return dataMap.get(thread);
		}
	}
	/**
	 * ConcurrentHashMap 线程安全的类 、HashMap 非线程安全的类（使用会出现脏数据）
	 */
	private static Map<Thread, Object> dataMap = new ConcurrentHashMap<Thread, Object>();
	public static void ShareDateOnlyThread(){
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Object data = new Random().nextInt();
					dataMap.put(Thread.currentThread(), data);
					System.out.println("Thread:"+Thread.currentThread().getName()+" has loaded data -> "+data);
					System.out.println("Thread:"+Thread.currentThread().getName()+" A.getData() -> "+new C().getData(Thread.currentThread()));
					System.out.println("Thread:"+Thread.currentThread().getName()+" B.getData() -> "+new D().getData(Thread.currentThread()));
				}
			}).start();
			
		}
	}
	
	/**
	 * main method 
	 */
	public static void main(String[] args) {
//		ThreadScopeShareData.shareDataDemo();
		ThreadScopeShareData.ShareDateOnlyThread();
		
	}
}

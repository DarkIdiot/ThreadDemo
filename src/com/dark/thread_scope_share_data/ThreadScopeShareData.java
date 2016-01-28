package com.dark.thread_scope_share_data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * �̰߳����ݶ��󣬶��ڲ�ͬ���̣߳�ȡ�ò�ͬ��ֵ����ʹ��Map<Thread,Object>ʵ�֣�
 * @author idiot
 * @version 1.0
 * @date 2016��1��18�� ����9:23:29
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
					 * ����ͬ������Ϊ�˸��ӷ���Ŀ���Ч�����ڲ���ͬ����������£����ѵõ�������ͬ��data����,��һ���߳����ǻḲ��ǰ����߳�װ������ݡ�
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
	 * ConcurrentHashMap �̰߳�ȫ���� ��HashMap ���̰߳�ȫ���ࣨʹ�û���������ݣ�
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

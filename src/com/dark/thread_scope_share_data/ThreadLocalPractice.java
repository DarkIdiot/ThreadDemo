package com.dark.thread_scope_share_data;

import java.util.Random;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��19�� ����12:16:22
 */
public class ThreadLocalPractice {
	/**
	 * simple primitive variable ThreadLocal demo
	 */
	private static ThreadLocal<Object> data = new ThreadLocal<Object>();
	static class A {
		public Object getData(){
			return data.get();
		}
	}
	static class B {
		public Object getData(){
			return data.get();
		}
	}
	public static void ThreadLocalDemo() {
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Object tempData = new Random().nextInt();
					System.out.println("Thread:"+Thread.currentThread().getName()+" has loaded data -> "+tempData);
					data.set(tempData);
					System.out.println("Thread:"+Thread.currentThread().getName()+" A.getData() -> "+new A().getData());
					System.out.println("Thread:"+Thread.currentThread().getName()+" B.getData() -> "+new B().getData());
				}
			}).start();
		}
	}
	/**
	 * simple Object ThreadLocal demo
	 */
	static class User {
		private String name;
		private String password;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public String toString() {
			return "User [name=" + name + ", password=" + password + "]";
		}
	}
	private  static ThreadLocal<User> user = new ThreadLocal<ThreadLocalPractice.User>();
	public static void ThreadLocalDemo4User(){
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int randomInt = new Random().nextInt();
					User  u = new User();
					u.setName("username"+randomInt);
					u.setPassword("user"+randomInt+"'s password");
					user.set(u);
					System.out.println("Thread:"+Thread.currentThread().getName()+" has loaded data -> "+u);
					System.out.println("Thread:"+Thread.currentThread().getName()+" user -> "+user.get());
				}
			}).start();
		}
	}
	/**
	 * advance ThreadLocal demo (by used hungry singleton designer pattern )(struts 2 ��action�����˼��.)
	 */
	static class  ThreadData{
		
		private String others;
		private User user;
		
		private static ThreadLocal<ThreadData> ThreadContainer = new ThreadLocal<ThreadLocalPractice.ThreadData>();

		/**
		 * private constructor method
		 */
		private ThreadData() {
		}
		
		public static ThreadData getInstance(){
			ThreadData instance = ThreadContainer.get();
			if (instance == null) {
				instance = new ThreadData();
				ThreadContainer.set(instance);
			}
			return instance;
		}
		public String getOthers() {
			return others;
		}
		public void setOthers(String others) {
			this.others = others;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		@Override
		public String toString() {
			return "ThreadData [others=" + others + ", user=" + user + "]";
		}
	}
	public static void ThreadLocalDemoAdvance(){
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					ThreadData data = ThreadData.getInstance();
					int randomInt = new Random().nextInt();
					User  u = new User();
					u.setName("username"+randomInt);
					u.setPassword("user"+randomInt+"'s password");
					data.setUser(u);
					data.setOthers("Others....."+randomInt);
					System.out.println("Thread:"+Thread.currentThread().getName()+" has loaded data -> "+data);
					System.out.println("Thread:"+Thread.currentThread().getName()+" user -> "+ ThreadData.ThreadContainer.get());
				}
			}).start();
		}
	}
	/**
	 * main method 
	 */
	public static void main(String[] args) {
//		ThreadLocalPractice.ThreadLocalDemo();
//		ThreadLocalPractice.ThreadLocalDemo4User();
		ThreadLocalPractice.ThreadLocalDemoAdvance();
	}
}

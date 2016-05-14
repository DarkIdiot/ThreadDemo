package com.dark.traditional_thread;

/**
 * 多线程机制会导致性能变低；但是多线程技术运用到服务器的后台，会使用线程技术为用户提高带宽，进而让用户感受到响应速度的提升。
 * @author idiot
 * @version 1.0
 * @date 2016年1月15日 上午9:00:08
 */
public class TraditionalThread {
	public static void main(String[] args) {
		
		/**
		 * new Thread 的子类实例，覆盖父类Thread的run()方法。
		 */
		Thread thread = new Thread(){
			@Override
			public void run() {
				while (true) {
					/**
					 * Thread.currentThread()获取当前线程的引用
					 */
					System.out.println("Thread.currentThread().getName():"+Thread.currentThread().getName());
					System.out.println("this.getName():"+this.getName());
				}
			}
		};
		thread.start();
		
		/**
		 * new Runnable的实现类，调用Thread的带Runnable参数的构造器，为target初始化。
		 * 由Thread类中的run()方法可以得知，为target提供实现，覆写Runnable的run()方法。
		 */
		Thread runnable = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println("Thread.currentThread().getName():"+Thread.currentThread().getName());
					System.out.println("this.getClass():"+this.getClass());
				}
			}
		});
		runnable.start();
		
		/**
		 * 同时覆写Thread和Runnable的Run()方法,只执行Thread的Run()方法。
		 * 
		 */
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					System.out.println("Thread.currentThread().getName():"+Thread.currentThread().getName());
					System.out.println("this.getClass():"+this.getClass());
				}				
			}
		}){
			public void run() {
				while (true) {
					System.out.println("Thread.currentThread().getName():"+Thread.currentThread().getName());
					System.out.println("this.getClass():"+this.getClass());
				}
			};
		}.start();
		
		
	}
}
/**
 * class Thread{
 * private Runnable target; 
 * public void run(){
 * 	if(target != null){
 * 		target.run();
 * 	}
 * }
 * 
 * }
 */


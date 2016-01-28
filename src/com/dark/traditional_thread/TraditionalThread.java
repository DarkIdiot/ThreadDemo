package com.dark.traditional_thread;

/**
 * ���̻߳��ƻᵼ�����ܱ�ͣ����Ƕ��̼߳������õ��������ĺ�̨����ʹ���̼߳���Ϊ�û���ߴ����������û����ܵ���Ӧ�ٶȵ�������
 * @author idiot
 * @version 1.0
 * @date 2016��1��15�� ����9:00:08
 */
public class TraditionalThread {
	public static void main(String[] args) {
		
		/**
		 * new Thread ������ʵ�������Ǹ���Thread��run()������
		 */
		Thread thread = new Thread(){
			@Override
			public void run() {
				while (true) {
					/**
					 * Thread.currentThread()��ȡ��ǰ�̵߳�����
					 */
					System.out.println("Thread.currentThread().getName():"+Thread.currentThread().getName());
					System.out.println("this.getName():"+this.getName());
				}
			}
		};
		thread.start();
		
		/**
		 * new Runnable��ʵ���࣬����Thread�Ĵ�Runnable�����Ĺ�������Ϊtarget��ʼ����
		 * ��Thread���е�run()�������Ե�֪��Ϊtarget�ṩʵ�֣���дRunnable��run()������
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
		 * ͬʱ��дThread��Runnable��Run()����,ִֻ��Thread��Run()������
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


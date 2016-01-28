package com.dark.thread_scope_share_data;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月22日 上午11:07:35
 */
public class MultiThreadShareData {
	private int i = 10000;
	private void increase(){
		i++;
	}
	private void decrease(){
		i--;
	}
	class MyRunnableInc implements Runnable{
		private MultiThreadShareData data;
		public MyRunnableInc(MultiThreadShareData data) {
			this.data = data;
		}
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				data.increase();
				System.err.println(Thread.currentThread().getName()+ "-->" +i);
			}
		}
	}
	class MyRunnableDec implements Runnable{
		private MultiThreadShareData data;
		public MyRunnableDec(MultiThreadShareData data) {
			this.data = data;
		}
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				data.decrease();
				System.err.println(Thread.currentThread().getName()+ "-->" +i);
			}
		}
	}
	/**
	 * main method
	 */
	public static void main(String[] args) {
		MultiThreadShareData data = new MultiThreadShareData();
		new Thread(data.new MyRunnableDec(data)).start();
		new Thread(data.new MyRunnableInc(data)).start();
	}
}

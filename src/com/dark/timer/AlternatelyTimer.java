package com.dark.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月16日 下午10:39:59
 */
public class AlternatelyTimer {
	/**
	 * 在TimerTask中定义新的Timer，并装入当前的TimerTask会抛出异常。
	 */
	public static void errorDemo() {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("AnonymousTimerTask:Timer_Bombing...");
				/**
				 * java.lang.IllegalStateException: Task already scheduled or cancelled
				 * TimerTask使用过后就不能再被装入Timer，必须重新new出来一个新的TimerTask，否则会出现上面的异常
				 */
				// == error code demo === new Timer().schedule(this, 2000);
			}
		}, 2000);
		
		while (true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 *  通过static变量辨识，分别定时同一个TimerTask demo
	 */
	static class CustomTimerTask extends TimerTask{
		static int count = 0; 
		@Override
		public void run() {
			if ((count++)%2 == 1) {
				new Timer().schedule(new CustomTimerTask(), 2000);
				System.out.println("CustomTimerTask:Timer_Bombing...");
			}else {
				new Timer().schedule(new CustomTimerTask(), 3000);
				System.out.println("CustomTimerTask:Timer_Bombing...");
			}
		}
	}	
	public static void AlternatelyDemo(){
		new Timer().schedule(new CustomTimerTask(), 2000);
		while (true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 分别创建二个TimerTask的子类，在各自的run()方法中分别实例化一个Timer并绑定其他的TimerTask，实现了交互式的定时任务。
	 */
	static class Custom1TimerTask extends TimerTask{
		@Override
		public void run() {
				System.out.println("CustomTimer1Task:Timer_Bombing...");
				new Timer().schedule(new Custom2TimerTask(), 2000);
		}
	}	
	static class Custom2TimerTask extends TimerTask{
		@Override
		public void run() {
				System.out.println("CustomTimer2Task:Timer_Bombing...");
				new Timer().schedule(new Custom1TimerTask(), 1000);
		}
	}	
	public static void AlternatelyTimerTaskDemo(){
		new Timer().schedule(new Custom1TimerTask(), 2000);
		while (true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * main method
	 */
	public static void main(String[] args) {
		//AlternatelyTimer.errorDemo();
		//AlternatelyTimer.AlternatelyDemo();
		AlternatelyTimer.AlternatelyTimerTaskDemo();
	}
}

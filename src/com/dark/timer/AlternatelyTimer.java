package com.dark.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��16�� ����10:39:59
 */
public class AlternatelyTimer {
	/**
	 * ��TimerTask�ж����µ�Timer����װ�뵱ǰ��TimerTask���׳��쳣��
	 */
	public static void errorDemo() {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("AnonymousTimerTask:Timer_Bombing...");
				/**
				 * java.lang.IllegalStateException: Task already scheduled or cancelled
				 * TimerTaskʹ�ù���Ͳ����ٱ�װ��Timer����������new����һ���µ�TimerTask����������������쳣
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
	 *  ͨ��static������ʶ���ֱ�ʱͬһ��TimerTask demo
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
	 * �ֱ𴴽�����TimerTask�����࣬�ڸ��Ե�run()�����зֱ�ʵ����һ��Timer����������TimerTask��ʵ���˽���ʽ�Ķ�ʱ����
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

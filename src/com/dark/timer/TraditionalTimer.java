package com.dark.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��16�� ����10:25:47
 */
public class TraditionalTimer {
	/**
	 * �򵥵Ķ�ʱ��demo
	 */
	public static void timerDemo4Delay() {
		new Timer().schedule(new TimerTask(){
			@Override
			public void run() {
				System.out.println("Timer1_Bombing...");
			}
		}, 1000);
		new Timer().schedule(new TimerTask(){
			@Override
			public void run() {
				System.out.println("Timer2_Bombing...");
			}
		}, 1000,3000);
		while (true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void timerDemo4SpecifiedDate() {
		/**
		 * new Timer().schedule(TimerTask timerTask,Date date);
		 * ����dateָ����ʱ������
		 */
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Date date = new Date();
				System.out.println(date.getHours()+":"+date.getSeconds());
			}
		},
		/**
		 * 5���
		 */
		new Date(System.currentTimeMillis()+ 1000*5));
		/**
		 * new Timer().schedule(TimerTask timerTask,Date date,Long period);
		 * ����dateָ����ʱ�����������Ҽ��period������ٴ�����
		 */
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Date date = new Date();
				System.out.println(date.getHours()+":"+date.getSeconds());
			}
		},
		/**
		 * 8���
		 */
		new Date(System.currentTimeMillis()+ 1000*8),2000L);
		
		
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
		//TraditionalTimer.timerDemo4Delay();
		TraditionalTimer.timerDemo4SpecifiedDate();
	}
}

package com.dark.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月16日 下午10:25:47
 */
public class TraditionalTimer {
	/**
	 * 简单的定时器demo
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
		 * 按照date指定的时间启动
		 */
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Date date = new Date();
				System.out.println(date.getHours()+":"+date.getSeconds());
			}
		},
		/**
		 * 5秒后
		 */
		new Date(System.currentTimeMillis()+ 1000*5));
		/**
		 * new Timer().schedule(TimerTask timerTask,Date date,Long period);
		 * 按照date指定的时间启动，并且间隔period毫秒后再次运行
		 */
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Date date = new Date();
				System.out.println(date.getHours()+":"+date.getSeconds());
			}
		},
		/**
		 * 8秒后
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

package com.dark.mutually_exclusive;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月17日 下午6:42:30
 */
public class TraditionalThreadSynchronized {
	/**
	 * Synchronized Demo
	 */
	class Outputer {
		public void output(String name) {
			synchronized (this) {
				int len = name.length();
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
	}
	public static void SynchroizedDemo() {
		Outputer outputer = new TraditionalThreadSynchronized().new Outputer();
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("123456789");
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("ABCDEFGHI");
				}
			}
		}.start();
	}
	/**
	 * Alternate Synchronized Demo equal to upper demo
	 */
	class OutputerAlternate {
		public synchronized void output4Number(String name) {
				int len = name.length();
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
		}
		public synchronized void output4Alphabet(String name) {
				int len = name.length();
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
		}
	}
	public static void SynchroizedAlternateDemo() {
		OutputerAlternate outputer = new TraditionalThreadSynchronized().new OutputerAlternate();
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output4Number("123456789");
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output4Alphabet("ABCDEFGHI");
				}
			}
		}.start();
	}
	/**
	 * Convert Alternate Synchronized Demo equal to upper demo
	 * Limited by synchronized plain method equal to " synchronized (this) " (instance Object)
	 * Limited by synchronized static method equal to " synchronized (OutputerAlternateConvert.class) " (class Object)
	 */
	static class OutputerAlternateConvert {
		public void output4Number(String name) {
			synchronized (OutputerAlternateConvert.class) {
				int len = name.length();
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
		public synchronized static void output4Alphabet(String name) {
				int len = name.length();
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
		}
	}
	public static void SynchroizedAlternateConvertDemo() {
		OutputerAlternateConvert outputer = new OutputerAlternateConvert();
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output4Number("123456789");
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					OutputerAlternateConvert.output4Alphabet("ABCDEFGHI");
				}
			}
		}.start();
	}
	
	/**
	 * mian method
	 */
	public static void main(String[] args) {
		//TraditionalThreadSynchronized.SynchroizedDemo();
		//TraditionalThreadSynchronized.SynchroizedAlternateDemo();
		TraditionalThreadSynchronized.SynchroizedAlternateConvertDemo();
	}
}

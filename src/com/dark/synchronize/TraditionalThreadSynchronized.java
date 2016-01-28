package com.dark.synchronize;

/**
 * 实现子线程循环10次，主线程循环100次，主/子线程都循环50次
 * 
 * @author idiot
 * @version 1.0
 * @date 2016年1月17日 下午9:45:08
 */
public class TraditionalThreadSynchronized {

	/**
	 *  error demo 1 by used {@link TraditionalThreadSynchronized.class}
	 */
	/**
	 * 匿名内部类可以访问类的静态变量。
	 */
	private static boolean ruleFlag = false;                   
	public  static void SychronizeDemo() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					while (ruleFlag) {
						try {
							TraditionalThreadSynchronized.class.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					synchronized (TraditionalThreadSynchronized.class) {
						for (int j = 1; j <= 10; j++) {
							System.out.println("sub thread sequence of " + j
									+ ", loop of" + i);
						}
					}
					ruleFlag = false;
					/**
					 * error demo by {@link java.lang.IllegalMonitorStateException}
					 * 违法的监控状态异常。当某个线程试图等待一个自己并不拥有的对象（O）的监控器或者通知其他线程等待该对象（O）的监控器时，抛出该异常。
					 */
					TraditionalThreadSynchronized.class.notify();
				}
			}
		}).start();
		for (int i = 1; i <= 50; i++) {
			while (!ruleFlag) {
				try {
					/**
					 * error demo by {@link java.lang.IllegalMonitorStateException}
					 * 违法的监控状态异常。当某个线程试图等待一个自己并不拥有的对象（O）的监控器或者通知其他线程等待该对象（O）的监控器时，抛出该异常。
					 */
					TraditionalThreadSynchronized.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized (TraditionalThreadSynchronized.class) {
				for (int j = 1; j <= 100; j++) {
					System.out.println("main thread sequence of " + j
							+ ", loop of" + i);
				}
			}
			ruleFlag = true;
			/**
			 * error demo by {@link java.lang.IllegalMonitorStateException}
			 * 违法的监控状态异常。当某个线程试图等待一个自己并不拥有的对象（O）的监控器或者通知其他线程等待该对象（O）的监控器时，抛出该异常。
			 */
			TraditionalThreadSynchronized.class.notify();
		}
	}
	
	/**
	 * error demo 1 fix up by used {@link TraditionalThreadSynchronized.class}
	 * wait() and notify() method should be in synchronized code block, but throws {@link java.lang.IllegalMonitorStateException}
	 */
	public  static void SychronizeDemoFixUp() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					synchronized (TraditionalThreadSynchronized.class) {
					while (ruleFlag) {
						try {
							TraditionalThreadSynchronized.class.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
						for (int j = 1; j <= 10; j++) {
							System.out.println("sub thread sequence of " + j
									+ ", loop of" + i);
						}
					ruleFlag = false;
					TraditionalThreadSynchronized.class.notify();
					}
				}
			}
		}).start();
		for (int i = 1; i <= 50; i++) {
			synchronized (TraditionalThreadSynchronized.class) {
			while (!ruleFlag) {
				try {
					TraditionalThreadSynchronized.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				for (int j = 1; j <= 100; j++) {
					System.out.println("main thread sequence of " + j
							+ ", loop of" + i);
				}
			ruleFlag = true;
			TraditionalThreadSynchronized.class.notify();
			}
		}
	}
	/**
	 *  error demo 2 by used {@link TraditionalThreadSynchronized#this}
	 */
	private static boolean ruleFlag2 = false;                   
	public  void SychronizeDemo2() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					while (ruleFlag2) {
						try {
							TraditionalThreadSynchronized.this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					synchronized (TraditionalThreadSynchronized.this) {
						for (int j = 1; j <= 10; j++) {
							System.out.println("sub thread sequence of " + j
									+ ", loop of" + i);
						}
					}
					ruleFlag2 = false;
					/**
					 * error demo by {@link java.lang.IllegalMonitorStateException}
					 * 违法的监控状态异常。当某个线程试图等待一个自己并不拥有的对象（O）的监控器或者通知其他线程等待该对象（O）的监控器时，抛出该异常。
					 */
					TraditionalThreadSynchronized.this.notify();
				}
			}
		}).start();
		for (int i = 1; i <= 50; i++) {
			while (!ruleFlag2) {
				try {
					/**
					 * error demo by {@link java.lang.IllegalMonitorStateException}
					 * 违法的监控状态异常。当某个线程试图等待一个自己并不拥有的对象（O）的监控器或者通知其他线程等待该对象（O）的监控器时，抛出该异常。
					 */
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized (TraditionalThreadSynchronized.this) {
				for (int j = 1; j <= 100; j++) {
					System.out.println("main thread sequence of " + j
							+ ", loop of" + i);
				}
			}
			ruleFlag2 = true;
			/**
			 * error demo by {@link java.lang.IllegalMonitorStateException}
			 * 违法的监控状态异常。当某个线程试图等待一个自己并不拥有的对象（O）的监控器或者通知其他线程等待该对象（O）的监控器时，抛出该异常。
			 */
			this.notify();
		}
	}
	/**
	 *  error demo 2 fix up by used {@link TraditionalThreadSynchronized#this}
	 *  wait() and notify() method should be in synchronized code block, but throws {@link java.lang.IllegalMonitorStateException}
	 */
	public  void SychronizeDemo2FixUp() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					synchronized (TraditionalThreadSynchronized.this) {
					while (ruleFlag2) {
						try {
							TraditionalThreadSynchronized.this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
						for (int j = 1; j <= 10; j++) {
							System.out.println("sub thread sequence of " + j
									+ ", loop of" + i);
						}
					ruleFlag2 = false;
					TraditionalThreadSynchronized.this.notify();
					}
				}
			}
		}).start();
		for (int i = 1; i <= 50; i++) {
			synchronized (TraditionalThreadSynchronized.this) {
			while (!ruleFlag2) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				for (int j = 1; j <= 100; j++) {
					System.out.println("main thread sequence of " + j
							+ ", loop of" + i);
				}
			ruleFlag2 = true;
			this.notify();
			}
		}
	}
	/**
	 * synchronized demo v1
	 * 锁是放在要操作的资源的类的内部方法中，而不是放在线程代码中。(面向对象的方式编程)
	 */
	class Business {
		private boolean ruleFlag = false;
		public synchronized void sub(int i) {
			/*
			 * 在线程的运用中 此处 while 与 if 没有太大的区别，然而一般使用while较多。此处可以预防ruleFlag变量没有被修改而唤醒所有线程的情况，可以让线程继续进入等待，而不是继续执行下去。
			 * 因为while的安全性显然要高一些，可以有效的预防被假唤醒。
			 * if (ruleFlag) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}*/
			while (ruleFlag) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int j = 1; j <= 10; j++) {
				System.out.println("sub thread sequence of " + j + ", loop of"
						+ i);
			}
			ruleFlag = true;
			/**
			 * 为什么要在代码最后执行notify()方法？
			 *    会出现当2个线程同时开始的时候，sub Thread 慢一步再运行然后一直处于等待状态，就算ruleFlag改变，轮到sub Thread 
			 *    也一直等待下去，这时候就需要mian Thread 的最后一句代码来通知、唤醒所有线程
			 */
			this.notify();
		}

		public synchronized void main(int i) {
			/*
			 * 在线程的运用中 此处 while 与 if 没有太大的区别，然而一般使用while较多。此处可以预防ruleFlag变量没有被修改而唤醒所有线程的情况，可以让线程继续进入等待，而不是继续执行下去。
			 * 因为while的安全性显然要高一些，可以有效的预防被假唤醒。
			 * if (!ruleFlag) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}*/
			while (!ruleFlag) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int j = 1; j <= 100; j++) {
				System.out.println("main thread sequence of " + j + ", loop of"
						+ i);
			}
			ruleFlag = false;
			/**
			 * 为什么要在代码最后执行notify()方法？
			 *    会出现当2个线程同时开始的时候，main Thread 提前运行然后一直处于等待状态，就算ruleFlag改变，轮到main Thread 
			 *    也一直等待下去，这时候就需要sub Thread 的最后一句代码来通知、唤醒所有线程
			 */
			this.notify();
		}
	}

	public static void SychronizeDemoV1() {
		Business business = new TraditionalThreadSynchronized().new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub(i);
				}
			}
		}).start();
		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}
	}

	/**
	 * main method
	 */
	public static void main(String[] args) {
//		TraditionalThreadSynchronized.SychronizeDemo();
//		TraditionalThreadSynchronized.SychronizeDemoFixUp();
//		new TraditionalThreadSynchronized().SychronizeDemo2();
//		new TraditionalThreadSynchronized().SychronizeDemo2FixUp();
//		TraditionalThreadSynchronized.SychronizeDemoV1();
	}
	/**
	 * note: 匿名内部类调用的方法内局部变量必须为final;
	 * 首先，内部类被编译的时候会生成一个单独的内部类的.class文件，这个文件并不与外部类在同一class文件中。
	 * 当外部类传的参数被内部类调用时，从java程序的角度来看是直接的调用例如：
	 *  public void dosome(final String a,final int b){ 
	 *  	class Dosome{
	 *  		public void dosome(){
	 *  			System.out.println(a+b)}
	 *  		}; 
	 *  	Dosome some=new Dosome();
	 * 		some.dosome();
	 * }
	 * 从代码来看好像是那个内部类直接调用的a参数和b参数，但是实际上不是，在java编译器编译以后实际的操作代码是
	 * class Outer$Dosome{ 
	 * 		public Dosome(final String a,final int b){
	 * 			this.Dosome$a=a; 
	 * 			this.Dosome$b=b;
	 * 		}
	 * 		public void dosome(){
	 * 			System.out.println(this.Dosome$a+this.Dosome$b);
	 * 		}
	 * }
	 * 从以上代码看来，内部类并不是直接调用方法传进来的参数，而是内部类将传进来的参数通过自己的构造器备份到了自己的内部，
	 * 自己内部的方法调用的实际是自己的属性而不是外部类方法的参数。
	 */
}

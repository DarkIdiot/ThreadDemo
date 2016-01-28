package com.dark.synchronize;

/**
 * ʵ�����߳�ѭ��10�Σ����߳�ѭ��100�Σ���/���̶߳�ѭ��50��
 * 
 * @author idiot
 * @version 1.0
 * @date 2016��1��17�� ����9:45:08
 */
public class TraditionalThreadSynchronized {

	/**
	 *  error demo 1 by used {@link TraditionalThreadSynchronized.class}
	 */
	/**
	 * �����ڲ�����Է�����ľ�̬������
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
					 * Υ���ļ��״̬�쳣����ĳ���߳���ͼ�ȴ�һ���Լ�����ӵ�еĶ���O���ļ��������֪ͨ�����̵߳ȴ��ö���O���ļ����ʱ���׳����쳣��
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
					 * Υ���ļ��״̬�쳣����ĳ���߳���ͼ�ȴ�һ���Լ�����ӵ�еĶ���O���ļ��������֪ͨ�����̵߳ȴ��ö���O���ļ����ʱ���׳����쳣��
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
			 * Υ���ļ��״̬�쳣����ĳ���߳���ͼ�ȴ�һ���Լ�����ӵ�еĶ���O���ļ��������֪ͨ�����̵߳ȴ��ö���O���ļ����ʱ���׳����쳣��
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
					 * Υ���ļ��״̬�쳣����ĳ���߳���ͼ�ȴ�һ���Լ�����ӵ�еĶ���O���ļ��������֪ͨ�����̵߳ȴ��ö���O���ļ����ʱ���׳����쳣��
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
					 * Υ���ļ��״̬�쳣����ĳ���߳���ͼ�ȴ�һ���Լ�����ӵ�еĶ���O���ļ��������֪ͨ�����̵߳ȴ��ö���O���ļ����ʱ���׳����쳣��
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
			 * Υ���ļ��״̬�쳣����ĳ���߳���ͼ�ȴ�һ���Լ�����ӵ�еĶ���O���ļ��������֪ͨ�����̵߳ȴ��ö���O���ļ����ʱ���׳����쳣��
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
	 * ���Ƿ���Ҫ��������Դ������ڲ������У������Ƿ����̴߳����С�(�������ķ�ʽ���)
	 */
	class Business {
		private boolean ruleFlag = false;
		public synchronized void sub(int i) {
			/*
			 * ���̵߳������� �˴� while �� if û��̫�������Ȼ��һ��ʹ��while�϶ࡣ�˴�����Ԥ��ruleFlag����û�б��޸Ķ����������̵߳�������������̼߳�������ȴ��������Ǽ���ִ����ȥ��
			 * ��Ϊwhile�İ�ȫ����ȻҪ��һЩ��������Ч��Ԥ�����ٻ��ѡ�
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
			 * ΪʲôҪ�ڴ������ִ��notify()������
			 *    ����ֵ�2���߳�ͬʱ��ʼ��ʱ��sub Thread ��һ��������Ȼ��һֱ���ڵȴ�״̬������ruleFlag�ı䣬�ֵ�sub Thread 
			 *    Ҳһֱ�ȴ���ȥ����ʱ�����Ҫmian Thread �����һ�������֪ͨ�����������߳�
			 */
			this.notify();
		}

		public synchronized void main(int i) {
			/*
			 * ���̵߳������� �˴� while �� if û��̫�������Ȼ��һ��ʹ��while�϶ࡣ�˴�����Ԥ��ruleFlag����û�б��޸Ķ����������̵߳�������������̼߳�������ȴ��������Ǽ���ִ����ȥ��
			 * ��Ϊwhile�İ�ȫ����ȻҪ��һЩ��������Ч��Ԥ�����ٻ��ѡ�
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
			 * ΪʲôҪ�ڴ������ִ��notify()������
			 *    ����ֵ�2���߳�ͬʱ��ʼ��ʱ��main Thread ��ǰ����Ȼ��һֱ���ڵȴ�״̬������ruleFlag�ı䣬�ֵ�main Thread 
			 *    Ҳһֱ�ȴ���ȥ����ʱ�����Ҫsub Thread �����һ�������֪ͨ�����������߳�
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
	 * note: �����ڲ�����õķ����ھֲ���������Ϊfinal;
	 * ���ȣ��ڲ��౻�����ʱ�������һ���������ڲ����.class�ļ�������ļ��������ⲿ����ͬһclass�ļ��С�
	 * ���ⲿ�ഫ�Ĳ������ڲ������ʱ����java����ĽǶ�������ֱ�ӵĵ������磺
	 *  public void dosome(final String a,final int b){ 
	 *  	class Dosome{
	 *  		public void dosome(){
	 *  			System.out.println(a+b)}
	 *  		}; 
	 *  	Dosome some=new Dosome();
	 * 		some.dosome();
	 * }
	 * �Ӵ��������������Ǹ��ڲ���ֱ�ӵ��õ�a������b����������ʵ���ϲ��ǣ���java�����������Ժ�ʵ�ʵĲ���������
	 * class Outer$Dosome{ 
	 * 		public Dosome(final String a,final int b){
	 * 			this.Dosome$a=a; 
	 * 			this.Dosome$b=b;
	 * 		}
	 * 		public void dosome(){
	 * 			System.out.println(this.Dosome$a+this.Dosome$b);
	 * 		}
	 * }
	 * �����ϴ��뿴�����ڲ��ಢ����ֱ�ӵ��÷����������Ĳ����������ڲ��ཫ�������Ĳ���ͨ���Լ��Ĺ��������ݵ����Լ����ڲ���
	 * �Լ��ڲ��ķ������õ�ʵ�����Լ������Զ������ⲿ�෽���Ĳ�����
	 */
}

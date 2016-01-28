package com.dark.concurrent.atomic;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.BinaryOperator;


/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��25�� ����4:25:58
 */
public class AtomicFieldUpdaterDemo {
	/**
	 * ���ֶ���������Ϊprivate.()
	 * ���ֶα��뱻 volatile����.(java.lang.IllegalArgumentException: Must be volatile type)
	 * 		volatile�������ǣ� ��Ϊָ��ؼ��֣�ȷ������ָ�������������Ż���ʡ�ԣ���Ҫ��ÿ��ֱ�Ӷ�ֵ.
	 */
	private volatile Watchdog dog;
	
	private static int count = 0;
	
	/**
	 * AtomicReferenceFieldUpdater demo
	 */
	public static void atomicReferenceFieldUpdater() {
		AtomicFieldUpdaterDemo atomicFieldUpdater = new AtomicFieldUpdaterDemo();
		AtomicReferenceFieldUpdater<AtomicFieldUpdaterDemo, Watchdog> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(AtomicFieldUpdaterDemo.class, Watchdog.class,"dog");
		for (int i = 0; i < 2; i++) {
			new Thread(()->{
				boolean flag;
				do{
					/**
					 * �������ֶεĶ������ 	Class<U> tclass
					 * �������µĶ������		Class<W> vclass
					 * �������µ��ֶε����� 	String fieldName
					 */
					Watchdog wd = null;
					synchronized (AtomicFieldUpdaterDemo.class) {
						count ++;
						 wd = new Watchdog(Thread.currentThread().getName()+"_watchDog_"+count);
					}
					wd = atomicReferenceFieldUpdater.accumulateAndGet(atomicFieldUpdater,wd, (o,n)->n);
					System.out.println(Thread.currentThread().getName()+" "+wd);
					/**
					 * �����µĶ���ʵ��	T obj
					 * ������referenceFieldȡֵ V expect
					 * �������õ�referenceFieldȡֵ  V update
					 */
					flag = atomicReferenceFieldUpdater.compareAndSet(atomicFieldUpdater, wd, new Watchdog(Thread.currentThread().getName()+"_WatchDog"));
					System.out.println(Thread.currentThread().getName()+" flag  =  "+flag+", "+atomicReferenceFieldUpdater.get(atomicFieldUpdater));
					
					
				}while(!flag);
			}).start();
		}
	}

	/**
	 * main method
	 */
	public static void main(String[] args) {
		AtomicFieldUpdaterDemo.atomicReferenceFieldUpdater();
	}
}

class Watchdog {
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Watchdog(String name) {
		super();
		this.name = name;
	}
	public Watchdog() {
		super();
	}
	@Override
	public String toString() {
		return "Watchdog [name=" + name + "]";
	}
}

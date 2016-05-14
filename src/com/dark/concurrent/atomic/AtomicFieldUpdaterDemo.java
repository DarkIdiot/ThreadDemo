package com.dark.concurrent.atomic;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;


/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月25日 下午4:25:58
 */
public class AtomicFieldUpdaterDemo {
	/**
	 * 该字段作用域不能为private.()
	 * 该字段必须被 volatile修饰.(java.lang.IllegalArgumentException: Must be volatile type)
	 * 		volatile的作用是： 作为指令关键字，确保本条指令不会因编译器的优化而省略，且要求每次直接读值.
	 */
	private volatile Watchdog dog;
	
	private static int count = 0;
	
	/**
	 * AtomicReferenceFieldUpdater demo
	 */
	public static void atomicReferenceFieldUpdater() {
		AtomicFieldUpdaterDemo atomicFieldUpdater = new AtomicFieldUpdaterDemo();
		/**
		 * 包含该字段的对象的类 	Class<U> tclass
		 * 将被更新的对象的类		Class<W> vclass
		 * 将被更新的字段的名称 	String fieldName
		 */
		AtomicReferenceFieldUpdater<AtomicFieldUpdaterDemo, Watchdog> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(AtomicFieldUpdaterDemo.class, Watchdog.class,"dog");
		for (int i = 0; i < 2; i++) {
			new Thread(()->{
				boolean flag;
				do{
					Watchdog wd = null;
					synchronized (AtomicFieldUpdaterDemo.class) {
						count ++;
						 wd = new Watchdog(Thread.currentThread().getName()+"_watchDog_"+count);
					}
					wd = atomicReferenceFieldUpdater.accumulateAndGet(atomicFieldUpdater,wd, (o,n)->n);
					System.out.println(Thread.currentThread().getName()+" "+wd);
					/**
					 * 被更新的对象实例	T obj
					 * 期望的referenceField取值 V expect
					 * 需新设置的referenceField取值  V update
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

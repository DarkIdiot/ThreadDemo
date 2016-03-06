package com.dark.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;

/**
 * 基本数据在被进行加减或其他操作后，然后取出来 -- 这个动作可能会出现并发的问题。
 * 			在当前线程完成对共享数据的操作之后，在没有取出来之前，其他线程对其进行了操作。就会使得原来的线程取出的数据是自己并不期望得到的。
 * 因此，atomic包内的类对所有的写和取进行了绑定和同步。
 * @author idiot
 * @version 1.0
 * @date 2016年1月25日 上午9:30:41
 */
public class AtomicDemo {
	/**
	 * set(int newValue) : Sets to the given value.
	 * lazySet(int newValue) : Eventually sets to the given value.
	 * compareAndSet(int expect, int update) : Atomically sets the value to the given updated value if the current value == the expected value.
	 * weakCompareAndSet(int expect, int update) : Atomically sets the value to the given updated value if the current value == the expected value.
	 * get() : Gets the current value.
	 * addAndGet(int delta) : Atomically adds the given value to the current value and returns the new value.
	 * getAndAdd(int delta) : Atomically adds the given value to the current value and returns the old value.
	 * getAndSet(int newValue) : Atomically sets to the given value and returns the old value. 
	 * getAndDecrement() : Atomically decrements by one the current value. just like i++.
	 * getAndIncrement() : Atomically increments by one the current value. just like i--.
	 * incrementAndGet() : Atomically increments by one the current value. just like ++i.
	 * decrementAndGet() : Atomically decrements by one the current value. just like --i.
	 */
	
	/**
	 * AtomicInteger demo
	 */
	public static void atomicIntegerDemo(){
		final AtomicInteger ai = new AtomicInteger();
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					boolean flag;
					do{
						int result = ai.get();
						System.out.println(Thread.currentThread().getName()+" : "+"result = "+ result+" , AtomicInteger = "+ai.get());
						/* 
						 单线程下, compareAndSet返回永远为true,
						 多线程下, 在与result进行compare时, ai可能被其他线程set了新值,这时需要重新再取一遍再比较,如果还是没有拿到最新的值, 则一直循环下去, 直到拿到最新的那个值*/
						flag = ai.compareAndSet(result, result+1); // result + 1 就出现了并发的问题。
						System.out.println(Thread.currentThread().getName()+" : "+"flag = "+ flag+" , result = "+ result+" , AtomicInteger = "+ai.get());
					}while(!flag);
				}
			}).start();
		}
	}
	/**
	 * AtomicReference demo
	 */
	private enum State{
		NEW,INITIALIZING,INITIALIZED
	}
	public static void  atomicReferenceDemo(){
		final AtomicReference<State> init = new AtomicReference<AtomicDemo.State>();
		init.set(State.NEW);
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					boolean flag;
					do {
						State state =  init.get();
						State temp = init.accumulateAndGet(State.INITIALIZING,(s,u) -> u );  		//使用lambda表达式。 
						System.out.println("State : "+temp);
						System.out.println(Thread.currentThread().getName()+" : "+"state = "+ state+" , AtomicReference = "+init.get());
						flag = init.compareAndSet(state, State.INITIALIZED);
						System.out.println(Thread.currentThread().getName()+" : "+"flag = "+ flag+" , state = "+ state+" , AtomicReference = "+init.get());
					} while (!flag);
				}
			}).start();
		}
	}
	/**
	 * main method
	 */
	public static void main(String[] args) {
//		AtomicDemo.atomicIntegerDemo();
		AtomicDemo.atomicReferenceDemo();
	}
}
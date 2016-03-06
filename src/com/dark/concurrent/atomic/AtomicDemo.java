package com.dark.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;

/**
 * ���������ڱ����мӼ�������������Ȼ��ȡ���� -- ����������ܻ���ֲ��������⡣
 * 			�ڵ�ǰ�߳���ɶԹ������ݵĲ���֮����û��ȡ����֮ǰ�������̶߳�������˲������ͻ�ʹ��ԭ�����߳�ȡ�����������Լ����������õ��ġ�
 * ��ˣ�atomic���ڵ�������е�д��ȡ�����˰󶨺�ͬ����
 * @author idiot
 * @version 1.0
 * @date 2016��1��25�� ����9:30:41
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
						 ���߳���, compareAndSet������ԶΪtrue,
						 ���߳���, ����result����compareʱ, ai���ܱ������߳�set����ֵ,��ʱ��Ҫ������ȡһ���ٱȽ�,�������û���õ����µ�ֵ, ��һֱѭ����ȥ, ֱ���õ����µ��Ǹ�ֵ*/
						flag = ai.compareAndSet(result, result+1); // result + 1 �ͳ����˲��������⡣
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
						State temp = init.accumulateAndGet(State.INITIALIZING,(s,u) -> u );  		//ʹ��lambda���ʽ�� 
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
package com.dark.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger可以在两个线程之间交换数据，只能是2个线程，他不支持更多的线程之间互换数据。
 * 		当线程A调用Exchange对象的exchange()方法后，他会陷入阻塞状态，直到线程B也调用了
 * 		exchange()方法，然后以线程安全的方式交换数据，之后线程A和B继续运行
 * @author idiot
 * @version 1.0
 * @date 2016年1月30日 上午1:26:08
 */
public class ExchangerDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Exchanger<Object> exchanger = new Exchanger<Object>();
		service.execute(()->{
			try {
				String str = "'data for Thread 1.'";
				System.out.println(Thread.currentThread().getName()+" will exchange the "+str+" out");
				Thread.sleep((long) (Math.random()*5000));
				String data = (String) exchanger.exchange(str);
				System.out.println(Thread.currentThread().getName()+" has exchange the "+data+" out");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		service.execute(()->{
			try {
				String str = "'data for Thread 2.'";
				System.out.println(Thread.currentThread().getName()+" will exchange the "+str+" out");
				Thread.sleep((long) (Math.random()*5000));
				String data = (String) exchanger.exchange(str);
				System.out.println(Thread.currentThread().getName()+" has exchange the "+data+" out");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}

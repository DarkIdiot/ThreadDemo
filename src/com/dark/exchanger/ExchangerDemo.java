package com.dark.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟竭筹拷之锟戒交锟斤拷锟斤拷锟捷ｏ拷只锟斤拷锟斤拷2锟斤拷锟竭程ｏ拷锟斤拷锟斤拷支锟街革拷锟斤拷锟斤拷叱锟街拷浠ワ拷锟斤拷锟斤拷荨锟�
 * 		锟斤拷锟竭筹拷A锟斤拷锟斤拷Exchange锟斤拷锟斤拷锟絜xchange()锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷状态锟斤拷直锟斤拷锟竭筹拷B也锟斤拷锟斤拷锟斤拷
 * 		exchange()锟斤拷锟斤拷锟斤拷然锟斤拷锟斤拷锟竭程帮拷全锟侥凤拷式锟斤拷锟斤拷锟斤拷锟捷ｏ拷之锟斤拷锟竭筹拷A锟斤拷B锟斤拷锟斤拷锟斤拷锟斤拷
 * @author idiot
 * @version 1.0
 * @date 2016锟斤拷1锟斤拷30锟斤拷 锟斤拷锟斤拷1:26:08
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
		service.execute(()->{
			try {
				String str = "'data for Thread 3.'";
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
				String str = "'data for Thread 4.'";
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

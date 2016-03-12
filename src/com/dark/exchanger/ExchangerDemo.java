package com.dark.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger�����������߳�֮�佻�����ݣ�ֻ����2���̣߳�����֧�ָ�����߳�֮�以�����ݡ�
 * 		���߳�A����Exchange�����exchange()������������������״̬��ֱ���߳�BҲ������
 * 		exchange()������Ȼ�����̰߳�ȫ�ķ�ʽ�������ݣ�֮���߳�A��B��������
 * @author idiot
 * @version 1.0
 * @date 2016��1��30�� ����1:26:08
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

package com.dark.concurrent.lock_condition;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author idiot
 * @version 1.0
 * @date 2016��1��27�� ����3:25:25
 */
public class CacheDemo {
	public static void main(String[] args) {
		CacheData data = new CacheData();
		for (int i = 0; i < 3; i++) {
			new Thread(()->{
				data.processCachedData();
			}).start();
		}
	}
}

class CacheData {
	Object data;
	volatile boolean cacheValid;
	ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	/**
	 * ���룺�������� reader �� writer ���� ReentrantLock����ʽ���»�ȡ��ȡ����д������
	 * ��д���̱߳��ֵ�����д�������Ѿ��ͷź󣬲��������� reader ʹ�����ǡ����⣬writer���Ի�ȡ��ȡ�������������򲻳�����
	 * ������Ӧ�ó����У����ڵ��û�ص���Щ�ڶ�ȡ��״̬��ִ�ж�ȡ�����ķ����ڼ䱣��д����ʱ����������á�
	 * ��� reader ��ͼ��ȡд��������ô����Զ�����óɹ���
	 * 
	 * �����������뻹�����д��������Ϊ��ȡ������ʵ�ַ�ʽ�ǣ��Ȼ�ȡд������Ȼ���ȡ��ȡ��������ͷ�д���������ǣ��Ӷ�ȡ��������д�����ǲ����ܵġ�
	 */
	void processCachedData() {
		rwl.readLock().lock();
		if (!cacheValid) {
			// Must release read lock before acquiring write lock
			rwl.readLock().unlock();
			rwl.writeLock().lock();
			// Recheck state because another thread might have acquired write
			// lock and changed state before we did.
			if (!cacheValid) {
				data = getData();
				cacheValid = true;
			}
			// Downgrade by acquiring read lock before releasing write lock
			rwl.readLock().lock();
			// Unlock write, still hold read
			rwl.writeLock().unlock();
		}
		use(data);
		rwl.readLock().unlock();
	}

	private void use(Object data2) {
		System.out.println(data2);
	}

	private Object getData() {
		return "cache data.";
	}
}
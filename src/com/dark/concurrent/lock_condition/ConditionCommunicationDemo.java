package com.dark.concurrent.lock_condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月28日 下午5:23:35
 */
public class ConditionCommunicationDemo {
	public static void main(String[] args) {
		Business business = new Business();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				business.sub(i);
			}
		}).start();
		for (int i = 0; i < 10; i++) {
			business.main(i);
		}
	}
}

class Business {
	private ReentrantLock lock = new ReentrantLock();

	private boolean flag = false;

	private Condition conditionMain = lock.newCondition();
	private Condition conditionSub = lock.newCondition();

	public void main(int j) {

		lock.lock();
		try {
			while (flag) {
				conditionMain.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()+" main thread sequence of " + i + ",loop of "+ j);
			}
			flag = true;
			conditionSub.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sub(int j) {

		lock.lock();
		try {
			while (!flag) {
				conditionSub.await();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()+" sub thread sequence of " + i + ",loop of "+ j);
			}
			flag = false;
			conditionMain.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}
package com.dark.concurrent.lock_condition;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月27日 下午3:25:25
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
	 * 重入：此锁允许 reader 和 writer 按照 ReentrantLock的样式重新获取读取锁或写入锁。
	 * 在写入线程保持的所有写入锁都已经释放后，才允许重入 reader 使用它们。此外，writer可以获取读取锁，但反过来则不成立。
	 * 在其他应用程序中，当在调用或回调那些在读取锁状态下执行读取操作的方法期间保持写入锁时，重入很有用。
	 * 如果 reader 试图获取写入锁，那么将永远不会获得成功。
	 * 
	 * 锁降级：重入还允许从写入锁降级为读取锁，其实现方式是：先获取写入锁，然后获取读取锁，最后释放写入锁。但是，从读取锁升级到写入锁是不可能的。
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
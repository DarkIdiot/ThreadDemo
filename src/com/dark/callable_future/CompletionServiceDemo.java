package com.dark.callable_future;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月26日 下午4:17:04
 */
public class CompletionServiceDemo {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		CompletionService<String> completionService = new ExecutorCompletionService<String>(
				threadPool);
		for (int i = 0; i < 20; i++) {
			final int taskCount = i;
			completionService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					int sec = 100 * new Random().nextInt(10);
					Thread.sleep(sec);
					System.out.println(Thread.currentThread().getName()+" has sleeped with " +sec +" millisecond");
					return Thread.currentThread().getName()+"&task-"+taskCount;
				}
			});
		}
		for (int i = 0; i < 20; i++) {
			try {
				System.out.println(completionService.take().get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		threadPool.shutdown();
	}
}

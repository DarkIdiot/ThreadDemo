package com.dark.concurrent.lock_condition;
/**
 * Both Lock and synchronized temporarily allow others to obtain the lock when they are waiting. 
 * To stop waiting, a thread have to re-acquire the lock.
 * @author idiot
 * @version 1.0
 * @date 2016��1��28�� ����5:23:35
 */
public class ObjectCommunicationDemo {

    public static void main(String[] args) {

        ThreadA ta = new ThreadA("ta");

        synchronized(ta) { // ͨ��synchronized(ta)��ȡ������ta��ͬ������
            try {
                System.out.println(Thread.currentThread().getName()+" start ta");
                ta.start();

                System.out.println(Thread.currentThread().getName()+" block");
                ta.wait();    // �ȴ�

                System.out.println(Thread.currentThread().getName()+" continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadA extends Thread{

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            synchronized (this) { // ͨ��synchronized(this)��ȡ����ǰ�����ͬ������
                System.out.println(Thread.currentThread().getName()+" wakup others");
                notify();    // ���ѡ���ǰ�����ϵĵȴ��̡߳�
            }
        }
    }
}
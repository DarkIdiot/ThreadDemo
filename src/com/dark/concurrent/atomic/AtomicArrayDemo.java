package com.dark.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author idiot
 * @version 1.0
 * @date 2016年1月25日 下午2:44:34
 */
public class AtomicArrayDemo {
	private static final int  ARRAYSIZE = 2;
	/**
	 * AtomicReferenceArray Demo
	 */
	public static void AtomicArrayDemo(){
		final AtomicReferenceArray<Person> personList = new AtomicReferenceArray<Person>(ARRAYSIZE);
		for (int i = 0; i < 2; i++) {
			new Thread(()->{
				boolean flag = true;
				do{
					String threadName = Thread.currentThread().getName();
					Person[] persons = new Person[2];
					for (int j = 0; j < ARRAYSIZE; j++) {
						 persons[j] = personList.accumulateAndGet(j, new Person("user_"+threadName,""+j *10), (s,u)->{
							return u;
						});
						System.out.println(Thread.currentThread().getName()+" : person = "+persons[j]);
					}
					/**
					 * do something
					 */
					for (int j = 0; j < ARRAYSIZE; j++) {
						flag = personList.compareAndSet(j, persons[j], persons[j==0?1:0]);
						System.out.println(Thread.currentThread().getName()+" : After the change => flag = "+flag+",expected = "+persons[j]+", person = "+personList.get(j));
						if (!flag)  break;
					}
				}while(!flag);
			}).start();
		}
	}
	/**
	 * main method
	 */
	public static void main(String[] args) {
		AtomicArrayDemo.AtomicArrayDemo();
	}
}
class Person{
	private String name;
	private String age;
	
	public Person() {
		super();
	}
	public Person(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}
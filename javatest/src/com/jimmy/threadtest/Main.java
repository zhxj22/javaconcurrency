package com.jimmy.threadtest;

public class Main {
	
	public static void main(String[] args) {
		for (int i = 0; i <10; i++) {
			Calculator calculator=new Calculator(i);
			Thread thread=new Thread(calculator);
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.start();
		}
		
		Thread th = new Thread(new MyRunnable());
		th.start();
		Thread th2 = new Thread(new MyRunnable());
		th2.start();
		Thread th3 = new Thread(new MyRunnable());
		th3.start();
		MyThread th4 = new MyThread();
		th4.start();
		System.out.println("The main thread ends:" + Thread.currentThread());
	}
}

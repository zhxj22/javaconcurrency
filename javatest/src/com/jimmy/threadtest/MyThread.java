package com.jimmy.threadtest;

public class MyThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("thread is running, this thread do not have Runnable as interface");
	}

}

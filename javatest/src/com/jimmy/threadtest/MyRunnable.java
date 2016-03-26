package com.jimmy.threadtest;

public class MyRunnable implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread th = Thread.currentThread();
		System.out.println("The running thread's info" + th.toString());
		System.out.println("The running thread's info - name:" + th.getName());
		System.out.println("The running thread's info - id:" + th.getId());
		System.out.println("The running thread's info - priority" + th.getPriority());
		System.out.println("The running thread's info - state" + th.getState());
	}
	
}

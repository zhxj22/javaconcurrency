package com.jimmy.threadtest;

import java.util.concurrent.TimeUnit;

public class FiveSecTask implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int time = 5;
		while(time > 0){
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Util.printThread(Thread.currentThread());
			time --;
		}
	}
	public static void main(String[] args) {
		Thread th1 = new Thread(new FiveSecTask());
		Thread th2 = new Thread(new FiveSecTask());
		th1.start();
		
		try {
			//the jvm will wait this thread till the thread die
			th1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		th2.start();
	}
}

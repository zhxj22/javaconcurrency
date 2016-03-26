package com.jimmy.threadtest;

import java.util.Currency;

public class InfiniteTask implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println(String.format("%s is running", Thread.currentThread().getName()));
			System.out.println(String.format("it's status is %s", Thread.currentThread().getState()));
			System.out.println(String.format("if this thread is interrupted: %s", Thread.currentThread().isInterrupted()));
			//if it is intterupted, we exit
			if (Thread.currentThread().isInterrupted()) {
				return;
			}
		}
	}

}

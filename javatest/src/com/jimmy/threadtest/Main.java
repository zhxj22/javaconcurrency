package com.jimmy.threadtest;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {
	
	public static void main(String[] args) {
		
		//how to interrupt threads
		Thread infiniteThread = new Thread(new InfiniteTask());
		infiniteThread.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		infiniteThread.interrupt();
		System.out.println(infiniteThread.isInterrupted());
		System.out.println(infiniteThread.getState());
	}
}

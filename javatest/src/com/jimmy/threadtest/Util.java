package com.jimmy.threadtest;

import java.util.concurrent.TimeUnit;

public class Util {
	public static void printThread(Thread th){
		System.out.println(String.format("%s - %s - %s", th.getName(), th.getId(), th.getState()));
	}
	public static void printmsg(String str){
		System.out.println(str);
	}
	public static void sleep(int sec){
		try {
			TimeUnit.MILLISECONDS.sleep(sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

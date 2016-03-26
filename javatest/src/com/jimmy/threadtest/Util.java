package com.jimmy.threadtest;

public class Util {
	public static void printThread(Thread th){
		System.out.println(String.format("%s - %s - %s", th.getName(), th.getId(), th.getState()));
	}
}
